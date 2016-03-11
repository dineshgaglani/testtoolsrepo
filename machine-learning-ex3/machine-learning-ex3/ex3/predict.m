function p = predict(Theta1, Theta2, X)
%PREDICT Predict the label of an input given a trained neural network
%   p = PREDICT(Theta1, Theta2, X) outputs the predicted label of X given the
%   trained weights of a neural network (Theta1, Theta2)

% Useful values
m = size(X, 1);
num_labels = size(Theta2, 1);

% You need to return the following variables correctly 
p = zeros(size(X, 1), 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Complete the following code to make predictions using
%               your learned neural network. You should set p to a 
%               vector containing labels between 1 to num_labels.
%
% Hint: The max function might come in useful. In particular, the max
%       function can also return the index of the max element, for more
%       information see 'help max'. If your examples are in rows, then, you
%       can use max(A, [], 2) to obtain the max for each row.
%

% Add ones to the X data matrix
a1 = [ones(m, 1) X]; 
hofXMatrixLayer1 = a1 * transpose(Theta1);

z2 = sigmoid(hofXMatrixLayer1);
%Now we have a 5000 * 25 matrix in z2

a2 = [ones(m, 1) z2]; 
%Now we have a 5000 * 26 matrix in a2

hofXMatrixLayer2 = a2 * transpose(Theta2);
% This is the hThetaX values for our 5000 training examples - size: 5000 *
% 10

a3 = sigmoid(hofXMatrixLayer2);
%Now we have 10 probabilities - each element of which is the probability
%that the training example belongs to a class(a3[1,1] has the probability 
%of training set 1 belonging to class 1, a3[2,1] has the probablity of 
%training set 2 belonging to class 1) size - 5000 * 10

[maxValue, maxIndex] = max(a3, [], 2);
%Getting the index of max probable class

p = maxIndex;
% =========================================================================


end
