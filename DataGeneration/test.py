import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-10,10)
y = x**2

plt.plot(x,y)
plt.savefig("C:/projects/TH_MES/DataGeneration/testplot.png")