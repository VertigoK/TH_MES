import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(-10,10)
y = x**2

print(x, y)

plt.plot(x, y)
plt.savefig("C:\\projects\\TH_MES\\Test\\AJAX_Test\\WebContent\\plot\\run_python.png")