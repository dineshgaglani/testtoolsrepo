function [J, grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%


% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);
         
% You need to return the following variables correctly 
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% Add ones to the X data matrix
X = [ones(m, 1) X];

%Computing cost starts

    %computing hThetaPerLayer
    layer1HTheta = X * transpose(Theta1); % 5000 * 25 matrix

    a2=sigmoid(layer1HTheta);
    %Now we have 25 outputs per training set, so we have a 5000 * 25 matrix of
    %values between 0 and 1 in a2

    % Add ones to the a2 data matrix
    a2 = [ones(size(a2, 1), 1) a2];

    layer2HTheta = a2 * transpose(Theta2);
   
    a3=sigmoid(layer2HTheta);
    %Now we have 10 probabilities - each element of which is the probability
    %that the training example belongs to a class(a3[1,1] has the probability 
    %of training set 1 belonging to class 1, a3[2,1] has the probablity of 
    %training set 2 belonging to class 1) size - 5000 * 10
    
    %Now we compute the cost by comparing with values of y, first get a
    %matrix of y
    logicalY = [];
    for c = 1:num_labels
        logicalY = [logicalY (y == c)];
    end
    
    logResultProbablities = log(a3);%5000 * 10 matrix
    
    oneMinusLogResultProbabilities = log(1-a3);% 5000 * 10 matrix
    
    oneMinusLogicalY = 1 - logicalY;
    
    JVectorFirst = sum(logResultProbablities .* (-1 * logicalY));
    
    JVectorSecond = sum(oneMinusLogicalY .* oneMinusLogResultProbabilities);
    
    JVectorFull = sum(JVectorFirst - JVectorSecond);%JVector is cost per K, hence a 5000 * 1 vector
    
    J = sum(JVectorFull)/m;
    
    %Regularization of the cost function
    regTerm1 = Theta1;
    regTerm1(:,1) = 0;
    
    regTerm2 = Theta2;
    regTerm2(:,1) = 0;
    regTermSumScalar = sum(sum(regTerm1.*regTerm1)) +  sum(sum(regTerm2.*regTerm2));
    
    regTerm = regTermSumScalar * lambda/(2*m);
    
    J = J + regTerm;

% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
    %Calculate delta3
    d3Matrix = a3 - logicalY; %5000 by 10 minus 5000 by 10, resulting in 5000 by 10
  
    %Calculating delta2
    theta2WithoutBias = Theta2(:,2:end);
    d2MatrixPart = d3Matrix * theta2WithoutBias;  % 5000 * 10 matrix into 
    %10(represents values received by each node in layer 3) * 25(represents 
    %values sent by one node in layer 2) matrix, results in 5000 * 25 deltas
   
    sigmoidGradientZ2 = sigmoidGradient(layer1HTheta); % 5000 * 25 matrix
    d2Matrix = d2MatrixPart .* sigmoidGradientZ2; % 5000 * 25 matrix
    
    %xwithoutBias = X(:,2:end);
    Delta2 = transpose(d3Matrix) * a2;% 10 * 5000 into 5000 * 26 means 10 into 26
    Delta1 = transpose(d2Matrix) * X; % 25 * 5000 into 5000 * 401 means 25 * 401
   
    
    Theta1_grad = Delta1/m;
    Theta2_grad = Delta2/m;
    

%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%



















% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
