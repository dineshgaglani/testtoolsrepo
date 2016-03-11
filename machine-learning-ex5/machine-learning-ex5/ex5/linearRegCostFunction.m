function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%

%X = [ones(size(X, 1), 1) X];

%Multiply X and theta
xTheta = X * theta;

%Sum of squares of diff with actual value
diff = xTheta - y;
diffSq = diff .^ 2;
diffSqSum = sum(diffSq);

%Scale by 1/2m
unregularizedJ = diffSqSum/(2*m);

thetaWithoutTheta1 = theta(2:size(theta, 1));
regularizationTermTheta = sum(thetaWithoutTheta1 .^ 2);
regularizationTerm = regularizationTermTheta * lambda/(2*m);


J = unregularizedJ + regularizationTerm;

% =========================================================================

grad = grad(:);

unscaledGradient = transpose(X) * diff;
unregularizedGradient = unscaledGradient/m;

gradientRegularizationTerms = thetaWithoutTheta1 * (lambda/m);
gradientRegularizationTerms = [zeros(1,size(thetaWithoutTheta1, 2)) transpose(gradientRegularizationTerms)];

grad = unregularizedGradient + transpose(gradientRegularizationTerms);
end
