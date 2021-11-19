import doctest
import sys

abrir = sys.argv[1]
salir =  sys.argv[2]


f = open(abrir, "r") #abrir el archivo
resultado = f.read() #leer el archivo y meter su resultado en una variable

def esPalindromo(palin): #funcion para comprobar si es palindromo
    """
    >>> esPalindromo("11")
    True
    >>> esPalindromo("12")
    False
    """
    reves = palin[::-1] #se guarda en una variable la cadena dada la vuelta
    if (palin == reves): #si la cadena inicial es igual que al reves, es palindromo
        return True
    else:
        return False

def esPrimo(primo): #funcion para comprobar si es primo
    """
    >>> esPrimo(5)
    True
    >>> esPrimo(4)
    False
    """
    comprobar = False
    if (primo == 1): return True #si es uno siempre es primo
    if (primo == 2 or primo < 0): return False #si es 2 o menor que 0 no es primo
    for n in range(2, primo) :
        #si el numero es divisible por si mismo o alguno de sus divisores no es primo
        if (primo % n != 0) : comprobar = True
        else: 
            comprobar = False
            return False
        if (comprobar == True): return True
        
numeros = resultado.split("\n") #recibir la informacion del archivo, quitando \n
palindromos= []
primos = []
for i in numeros:
    #depende de si son palindromos o primos, se guardan en una lista u otra
    if (esPrimo(int(i))):
        primos.append(i) 
    if (esPalindromo(i)):
        palindromos.append(i)

#se escriben los resultados en el fichero de salida
with open(salir, mode ="w") as file:
    file.write("Hi han "+str(len(palindromos))+" palindoms\n")
    file.write("Hi han "+str(len(primos))+" cosins\n")
    comunes = set(palindromos) & set(primos)
    for i in comunes:
        file.write(str(i)+"\n")

doctest.testmod()