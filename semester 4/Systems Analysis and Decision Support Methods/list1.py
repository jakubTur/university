!pip install gurobipy #command for google colab
import numpy as np
import gurobipy as gp

model=gp.Model("vector")
n=6
vector=model.addVars(n, vtype=gp.GRB.BINARY, name="x")
k = np.random.randint(0, 10, size=n)
print(k)
B = 20
model.addConstr(gp.quicksum((k[i]*vector[i]) for i in range(n)) <= B, name="constraint")
weight = np.copy(k)
model.setObjective(gp.quicksum((weight[i]*vector[i]) for i in range(n)), gp.GRB.MAXIMIZE)
model.optimize()
for v in model.getVars():
  print(v.varName)
print("Result:", model.ObjVal)


model.addConstr(((weight[i]+list(reversed(k)[i])) for i in range(int(n/2))) == B, name="constraint2")
model.optimize()
