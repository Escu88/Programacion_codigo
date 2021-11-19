from barcode import EAN13
from barcode.writer import ImageWriter
import csv

alum = []
names = []
#Abrir el csv
with open('alumnos.csv',newline='') as csvFile:
    alumnos = csv.reader(csvFile)
    for row in alumnos:
        #guardar el nombre en una lista y el id en otra
        alum.append(row[1])
        names.append(row[0])

for i in range(len(alum)):
    #se rellena una string para que llegue a 12 huecos con 0 si no es suficientemente larga
    alum[i] = alum[i].zfill(12)
    with open(names[i]+'.jpeg', 'wb') as f:
        EAN13(alum[i], writer=ImageWriter()).write(f)# Se crea el EAN
print(alum)