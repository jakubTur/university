!pip install gurobipy #command for google colab
import numpy as np
import gurobipy as gp

#task 1 - set coverage problem 2d

def create_matrix(rows, cols):
    matrix = []
    for i in range(rows):
        row = [0 for j in range(cols)]
        matrix.append(row)
    return matrix
c=5
t=7
r=8
model=gp.Model("model")
distances=create_matrix(t,c)
circle_x=np.random.randint(0, 100, size=c)
circle_y=np.random.randint(0, 100, size=c)
triangle_x=np.random.randint(0, 100, size=t)
triangle_y=np.random.randint(0, 100, size=t)
for i in range(t):
  for j in range(c):
    dx=circle_x[j]-triangle_x[i]
    dy=circle_y[j]-triangle_y[i]
    distances[i][j]=math.sqrt(dx*dx + dy*dy)
matrix=model.addMVar(shape=(t,c), vtype=gp.GRB.BINARY, name="x")
x = model.addVars(t, c, vtype=GRB.BINARY, name="x")
for i in range(t):
    model.addConstr(gp.quicksum(x[i, j] for j in range(c) if distances[i][j] <= r) >= 1, name=f"cover_triangle_{i}")

model.optimize()
