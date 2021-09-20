import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import mpld3

x = np.linspace(-10,10)
y = x**2

fig = plt.figure(figsize=(5,5))
plt.plot(x,y)

# (1)
# plt.show()

# (2) open in html
# mpld3.show()

# (3) save a figure to a html file
with open('./Test/simple_plot.html', 'w') as fn:
    mpld3.save_html(fig, fn)