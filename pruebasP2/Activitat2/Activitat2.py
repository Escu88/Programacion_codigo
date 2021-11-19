f = open("Sudoku2.in", "r") #abrir el archivo
resultado = f.read() #leer el archivo y meter su resultado en una variable
array = resultado.split("\n") #convertirlo en un array dividiendolo por el \n
array2 = [[0 for i in range(9)]for i in range(9)]
for i in range(9):
        array2[i] = array[i].split(" ")

def esSudokuCorrecto(Array):
    conclusion = True
    for i in range(9):
        if (iguales(Array[i]) == False):
            conclusion = False

    if (conclusion == False or columns(Array) == False or cubos(Array) == False):
        return False


def iguales(x):
    if (len(x) == len(set(x))):
        return True
    else:
        return False
    
def columns(x):
    
    for i in range(9):
        lista_prov = []
        for j in range(9):
            lista_prov.append(x[j][i])
        if (iguales(lista_prov) == False):
            return False
         
    
def cubos(array):
    for i in range(0,9,3):
        for j in range(0,9,3):
            if (comprobar(array,i,j) == False):
                return False

def comprobar(array,x,y):
    cubo = []
    for i in range(3):
        cubo.extend(array[x+i][y:y+3])
    if (iguales(cubo) == False):
        return False

if (esSudokuCorrecto(array2) == False):
    print("Esta mal")
else:
    print("Esta bien")
