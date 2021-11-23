class Notes:
    notas ={}

    def __init__(self,dicc):
        self.notas = dicc
        
    def media(self):
        med=0
        numbers = list(self.notas.values())
        for i in numbers:
            #print(i)
            med += int(i)
        final = med/len(self.notas)
        return final

    def mediaSin(self,num):
        med=0
        numbers = list(self.notas.values())
        for i in numbers:
            if (int(i) != num):
                med += int(i)
        final = med/len(self.notas)
        return final

    def cuantos(dicc):
        return len(dicc)
    


fichero_entrada = open ('lista.txt','r')
lista = fichero_entrada.readlines()
#print(lista)
mapa = {}
for i in range(len(lista)):
    parte = lista[i].split("/")
    mapa[parte[0]] = parte[1].replace("\n","")
#print(mapa)

obj1 = Notes(mapa)
print(obj1.notas)
print(Notes.media(obj1))
print(Notes.mediaSin(obj1,8))


mapa2 = {1: 'Geeks', 2: 'For', 3: 'Geeks'}
print(Notes.cuantos(mapa2))

#obj2 = Notes(mapa2)
#print(obj2.notas)