function [J, grad] = cofiCostFunc(params, Y, R, num_users, num_movies, ...
                                  num_features, lambda)
%COFICOSTFUNC Collaborative filtering cost function
%   [J, grad] = COFICOSTFUNC(params, Y, R, num_users, num_movies, ...
%   num_features, lambda) returns the cost and gradient for the
%   collaborative filtering problem.
%

% Unfold the U and W matrices from params
X = reshape(params(1:num_movies*num_features), num_movies, num_features);
Theta = reshape(params(num_movies*num_features+1:end), ...
                num_users, num_features);

            
% You need to return the following values correctly
J = 0;
X_grad = zeros(size(X));
Theta_grad = zeros(size(Theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost function and gradient for collaborative
%               filtering. Concretely, you should first implement the cost
%               function (without regularization) and make sure it is
%               matches our costs. After that, you should implement the 
%               gradient and use the checkCostFunction routine to check
%               that the gradient is correct. Finally, you should implement
%               regularization.
%
% Notes: X - num_movies  x num_features matrix of movie features
%        Theta - num_users  x num_features matrix of user features
%        Y - num_movies x num_users matrix of user ratings of movies
%        R - num_movies x num_users matrix, where R(i, j) = 1 if the 
%            i-th movie was rated by the j-th user
%
% You should set the following variables correctly:
%
%        X_grad - num_movies x num_features matrix, containing the 
%                 partial derivatives w.r.t. to each element of X
%        Theta_grad - num_users x num_features matrix, containing the 
%                     partial derivatives w.r.t. to each element of Theta
%

[movieFeatureIndex, userPreferenceIndex] = find(R);

movieFeatureMatrix = X(movieFeatureIndex,:);
userPreferenceMatrix = Theta(userPreferenceIndex,:);
hypothesisVector = sum(movieFeatureMatrix .* userPreferenceMatrix, 2);

resRatingsVector = Y(sub2ind(size(Y), movieFeatureIndex, userPreferenceIndex));

J = (sum((hypothesisVector - resRatingsVector).^2))/2;

%No idea what m doing here - https://www.coursera.org/learn/machine-learning/module/Oag3J/discussions/MHmSQRYBEeWHGCIACznL2w/replies/R64pzBYCEeWHGCIACznL2w
fullMatrix =  X * transpose(Theta);
errorMatrix = fullMatrix - Y;
errorMatrix = errorMatrix .* R;

X_grad = errorMatrix  * Theta;
Theta_grad = transpose(errorMatrix) * X;
% =============================================================

grad = [X_grad(:); Theta_grad(:)];

end
