import json
import numpy as np
from training import *  # Assuming functions like tokenize, stem, etc., are defined here

import torch
import torch.nn as nn
from torch.utils.data import Dataset, DataLoader
from model import NeuralNet

if __name__ == "__main__":
    # Load the new JSON format
    with open('./B/intent2.json', 'r', encoding='utf-8') as f:
        intents = json.load(f)

    all_words = []
    tags = []
    xy = []

    for intent in intents['intents']:
        tag = intent['tag']
        tags.append(tag)
        
        for pattern in intent['patterns']:
            pattern = pattern.lower()  # Convert to lowercase
            w = tokenize(pattern)
            all_words.extend(w)
            xy.append((w, tag))

    # Increase training epochs and hidden size for better learning
    num_epochs = 2000  # Increased from 1000
    hidden_size = 16   # Increased from 8

    # Remove unwanted words (like punctuation) and apply stemming
    ignore_words = ['?', '!', '.', ',']
    all_words = [stem(w) for w in all_words if w not in ignore_words]
    all_words = sorted(set(all_words))  # Remove duplicates and sort the words
    tags = sorted(set(tags))  # Remove duplicates and sort the tags

    x_train = []
    y_train = []

    # Convert the sentences to bag of words representation and prepare labels
    for (pattern_sentence, tag) in xy:
        bag = bag_of_words(pattern_sentence, all_words)  # Convert sentence to bag of words
        x_train.append(bag)

        label = tags.index(tag)  # Find the index of the tag in the sorted tags list
        y_train.append(label)

    x_train = np.array(x_train)  # Convert list to numpy array for training
    y_train = np.array(y_train)  # Convert list to numpy array for labels

    # Dataset class for DataLoader
    class ChatDataset(Dataset):
        def __init__(self):
            self.n_samples = len(x_train)
            self.x_data = x_train
            self.y_data = y_train

        def __getitem__(self, index):
            return self.x_data[index], self.y_data[index]

        def __len__(self):
            return self.n_samples

    # Hyperparameters
    batch_size = 8
    hidden_size = 8
    output_size = len(tags)  # Number of output classes (tags)
    input_size = len(x_train[0])  # Size of input (bag of words)
    learning_rate = 0.001
    num_epochs = 1000

    dataset = ChatDataset()
    train_loader = DataLoader(
        dataset=dataset,
        batch_size=batch_size,
        shuffle=True,
        num_workers=0  # Use 0 workers on Windows to avoid multiprocessing issues
    )

    # Set up the device and model
    device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
    model = NeuralNet(input_size, hidden_size, output_size).to(device)

    # Loss and optimizer
    criterion = nn.CrossEntropyLoss()
    optimizer = torch.optim.Adam(model.parameters(), lr=learning_rate)

    # Training loop
    for epoch in range(num_epochs):
        for (words, labels) in train_loader:
            words = words.to(device)
            labels = labels.to(device)
            
            # Forward pass
            outputs = model(words)
            loss = criterion(outputs, labels)
            
            # Backward pass and optimization
            optimizer.zero_grad()
            loss.backward()
            optimizer.step()
            
        if (epoch + 1) % 100 == 0:
            print(f'Epoch [{epoch + 1}/{num_epochs}], Loss: {loss.item():.4f}')

    print(f'Final loss: {loss.item():.4f}')

    # Save the trained model and other information
    data = {
        "model_state": model.state_dict(),
        "input_size": input_size,
        "hidden_size": hidden_size,
        "output_size": output_size,
        "all_words": all_words,
        "tags": tags
    }

    FILE = "C:/Users/a3791/Desktop/mini_prj/B/dt.pth"  # Save model parameters
    torch.save(data, FILE)

    print(f'Training complete. File saved to {FILE}')
