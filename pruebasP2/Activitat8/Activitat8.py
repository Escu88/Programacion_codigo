import os
from os import listdir
from os import walk
from glob import glob
import shutil

#print(glob("*"))

f = []
name = None
#Se buscan todos los archivos en la carpeta actual y se guardan sus nombres completos en f
for (dirpath, dirnames, filenames) in walk("."):
    f.extend(filenames)
    print(f)

def folders(name):
    #Separar la extension del nombre del archivo
    extension = name.split(".")[1]
    #Si en la carpeta actual hay alguna carpeta con el nombre de una extension, se mueve el archivo a esta
    if (extension in glob("*")):
        shutil.move(name, extension)
    else:
        #Si en la carpeta actual no existe ninguna carpeta con ese nombre, se crea y se mueve el archivo a esta
        os.mkdir(extension)
        shutil.move(name, extension)

for i in range(len(f)):
    """Se envian todos los archivos a la funcion folders para distrubuirlos en carpetas
    menos Activitat8 para evitar problemas con esta"""
    if (f[i] != "Activitat8"):
        folders(f[i])

#Para crear el ejecutable he utilizado: python3 -m PyInstaller --onefile Activitat8.py