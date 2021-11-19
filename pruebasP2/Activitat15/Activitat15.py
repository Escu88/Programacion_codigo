import re
import pyperclip

#FRASE DE EJEMPLO PARA COPIAR
#  hoLa viva pyThon
f = open("lista.txt", "r") #abrir el archivo
res = f.readlines()
try:
    while True:
        frase = pyperclip.waitForNewPaste()
        frase = frase.lower()
        asteriscos = ""

        for i in range(len(res)):
            res[i] = res[i].replace("\n", "").lower()
            for j in range(len(res[i])):
                asteriscos += "*"
            pat1 = re.compile(".*"+res[i]+".*")
            
            if (pat1.match(frase)):
                s = "*"
                frase = frase.replace(res[i], ''.join([char*len(res[i]) for char in s]))
                asteriscos = ""
        pyperclip.copy(frase)
        print(frase)
except:
    print("\nSe ha cerrado el programa")