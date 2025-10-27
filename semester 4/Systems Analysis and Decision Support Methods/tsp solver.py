# simmulated annealing for tsp
import random
import math
n = 30
distances=[[0 for _ in range(n)] for _ in range(n)]


def generateProblem(n):
  for i in range(n):
    for j in range(i):
      if(i != j):
        dist = random.randint(1,1000)
        distances[i][j] = dist
        distances[j][i] = distances[i][j]

def getDistance(solution):
  distance = 0
  for i in range(n):
    j = solution[i].index(1)
    distance += distances[i][j]
  return distance

def generateSolution(n):
  solutionMatrix=[[0]*n for _ in range(n)]
  visited=[False] * n
  next=random.randint(0,n-1)
  first = next
  visited[first] = True
  path=[first]
  while len(path) != n:
    prev = next
    rand = random.randint(0,n-1)
    if(rand != prev and (not visited[rand])):
      next = rand
      solutionMatrix[prev][next] = 1
      path.append(next)
      visited[next] = True
  solutionMatrix[next][first] = 1
  path.append(first)
  print("cities visited:", path)
  return solutionMatrix

def neighboringSolution(solutionMatrix):
    path = []
    first = random.randint(0,n-1)
    current = first
    while len(path) != n:
      path.append(current)
      for j in range(n):
          if solutionMatrix[current][j] == 1:
            current = j
            break
    path.append(first)
    i, j = random.sample(range(1, n), 2)
    path[i], path[j] = path[j], path[i]
    matrix = [[0]*n for _ in range(n)]
    for i in range(len(path)-1):
        matrix[path[i]][path[i+1]] = 1
    return matrix

def linear(temp):
  return temp - 1
def exponential(temp):
  return temp * 0.9995
def logarithmic(temp):
  return temp / (1 + math.log(80))

def fineTune(initial):
  trial=[]
  iterations=[1000, 2000, 5000]
  neighbour = neighboringSolution(initial)
  for i in range(8000):
    trial.append(neighbour)
    neighbour = neighboringSolution(neighbour)
  coolingRates = [linear, exponential, logarithmic]
  initialTemperatures = [1000, 5000, 10000]
  repetitions = [1,5,10] #per temperature
  best = float('inf')
  parameters = None
  coeff = None
  for q in iterations:
    for i in coolingRates:
      for j in initialTemperatures:
        for k in repetitions:
          solution = simulatedAnnealing(distances, initial,  q, j, i,k,random.shuffle(trial.copy()))
          tempOptimal = getDistance(solution)
          if tempOptimal < best:
            best = tempOptimal
            parameters = (i,j,k,q)
  print(f"Simulated annealing fine tuned with: {parameters}")
  return simulatedAnnealing(distances, initial, parameters[3], parameters[1], parameters[0], parameters[2], None)

def simulatedAnnealing(distances, solution, iterations, temperature, coolingRate, repetitions, neighbours):
  bestSolution = solution
  potentialSolution = solution
  previousSolution = solution
  best = getDistance(solution)
  for i in range(iterations):
    for _ in range(repetitions):
      if neighbours is not None:
          if len(neighbours) == 0:
              break
          potentialSolution = neighbours.pop(0)
      else:
          potentialSolution = neighboringSolution(previousSolution)
      prevDistance, newDistance = getDistance(previousSolution), getDistance(potentialSolution)
      if newDistance < prevDistance:
          previousSolution = potentialSolution
          if newDistance < best:
              best = newDistance
              bestSolution = potentialSolution
              print(f"New best found: {best} at temperature: {temperature}")
      else:
          prob=math.exp((prevDistance - newDistance) / temperature)
          if (random.uniform(0, 1) < prob):
              previousSolution=potentialSolution
    temperature = coolingRate(temperature)
    if temperature <= 1:
        print(f"Temperature dropped below 1 at iteration {i}. Stopping.")
        break
    if neighbours is not None and len(neighbours) == 0:
        print(f"All neighbors exhausted at iteration {i}.")
        break

  print(f"Optimal solution with distance: {best} at temperature: {temperature} after iterations: {i}")
  return bestSolution


generateProblem(n)
print(distances)
sol = generateSolution(n)
minimize = fineTune(sol)
print(getDistance(minimize))