function idx = findClosestCentroids(X, centroids)
%FINDCLOSESTCENTROIDS computes the centroid memberships for every example
%   idx = FINDCLOSESTCENTROIDS (X, centroids) returns the closest centroids
%   in idx for a dataset X where each row is a single example. idx = m x 1 
%   vector of centroid assignments (i.e. each entry in range [1..K])
%

% Set K
K = size(centroids, 1);

% You need to return the following variables correctly.
idx = zeros(size(X,1), 1);

examples = size(X,1);

% ====================== YOUR CODE HERE ======================
% Instructions: Go over every example, find its closest centroid, and store
%               the index inside idx at the appropriate location.
%               Concretely, idx(i) should contain the index of the centroid
%               closest to example i. Hence, it should be a value in the 
%               range 1..K
%
% Note: You can use a for-loop over the examples to compute this.
%
for trainingExampleIdx = 1:examples
    
   trainingExampleIVector = X(trainingExampleIdx,:);
   %fprintf('Training example vector: ');
   %disp(trainingExampleIVector);
   centroidDistancesFromTrainingExampleVector = zeros(K, 1);
   
   for centroidIdx = 1:K
      centroidIVector = centroids(centroidIdx,:);
      %fprintf('Centroid vector: ');
      %disp(centroidIVector);
      distanceVector = (trainingExampleIVector - centroidIVector).^2;
      %fprintf('distanceVector: ');
      %disp(distanceVector);
      centroidDistancesFromTrainingExampleVector(centroidIdx) = sqrt(sum(distanceVector));
   end
   
   %fprintf('centroidDistancesFromTrainingExampleVector: ');
   %disp(centroidDistancesFromTrainingExampleVector);
   [M,I] = min(centroidDistancesFromTrainingExampleVector);
   %fprintf('MinIndex: ');
   %disp(I);
   idx(trainingExampleIdx) = I;
    
end






% =============================================================

end

