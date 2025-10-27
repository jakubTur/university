!pip install gurobipy #command for google colab
import numpy as np
import gurobipy as gp

#task 1

model=gp.Model("model")
m=1
n=3
C=11
matrix=model.addMVar(shape=(m, n), vtype=gp.GRB.BINARY, name="x")
processingTimes = np.random.randint(0, 10, size=n)
for j in range(m):
  model.addConstr(gp.quicksum(matrix[j, i] for i in range(n))==1, name="constraint")
c=model.addVar(C, vtype=gp.GRB.CONTINUOUS, name="c")
for j in range(m):
  model.addConstr(gp.quicksum(matrix[j, i]*processingTimes[i] for i in range(n))<=c, name="constraint2")
model.setObjective(gp.quicksum(matrix[j, i] * processingTimes[i] for j in range(m) for i in range(n)), gp.GRB.MINIMIZE)
model.optimize()

#task 2
p=3
tri=4
cir=10
model2=gp.Model("model2")
vector=model2.addMVar(p, vtype=gp.GRB.BINARY, name="x")
matrixDistances=model2.addMVar(shape=(cir,tri), vtype=gp.GRB.BINARY, name="y")
model2.addConstr(gp.quicksum(vector[i] for i in range (tri))<=p, name='constraint')
for j in range(tri):
  model2.addConstr(gp.quicksum((matrixDistances[j,i]-vector[i]) for i in range(cir))<=0, name="constraint2")
  model2.addConstr(gp.quicksum((matrixDistances[i,j]==1) for i in range(cir))==1, name="constraint3")
model.optimize()