import matplotlib.pyplot as plt

from os import listdir, remove

totalImprovement = 0
averageImprovement = 0
averageBestImprovement = 0
count = 0

for x in listdir():
        if "performance data.txt" in x:
                s=open(x).read()

                s=s.split('\\n')[:-1]
                s=[float(x) for x in s]
                count+=1
                plt.plot(s, label = x.split('.')[0])
                #plt.legend()
                #plt.grid()
                #plt.xlabel("Number of Iterations")
                #plt.ylabel("Average Improvement")
                #plt.title("Improvement of Swap Sequences when\nrunning simulated annealing on " + x.split('.')[0][:x.find('ted')] + "ted datasets")
                #plt.savefig(fname=x.split('.')[0]+".jpg", quality = 95, dpi = 1000)
                #plt.show()

plt.legend()
plt.grid()
plt.xlabel("Number of Iterations")
plt.ylabel("Average Improvement")
plt.title("Improvement of Swap Sequences Over x Iterations when\nrunning simulated annealing over different datasets")
plt.savefig(fname="All Graphs.jpg", quality = 95, dpi = 1000)
plt.show()
