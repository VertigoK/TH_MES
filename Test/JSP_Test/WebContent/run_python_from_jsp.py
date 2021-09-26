import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(-10,10)
y = x**2

print(x, y)

plt.plot(x, y)
plt.savefig("C:\\projects\\TH_MES\\Test\\JSP_Test\\WebContent\\run_python_from_jsp.png")