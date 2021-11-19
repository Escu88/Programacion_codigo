import requests  #Importamos la librería requests
#especie = input()
especie = "human"
print("SOLICITANDO INFORMACION DE INTERNET")
print("espere....") 
URL = "https://rickandmortyapi.com/api/character/?species="+especie #configuramos la url
#solicitamos la información y guardamos la respuesta en data.
dataPage = requests.get(URL) 
dataPage = dataPage.json() #convertimos la respuesta en dict
paginas = dataPage['info']['pages']
cantidad = dataPage['info']['count']
#for element in data: #iteramos sobre data
    #print(element) #Accedemos a sus valores
#print("Total de "+str(especie)+" "+str(data['info']['count']))

j = 0
for i in range(1,paginas+1):
    URL = "https://rickandmortyapi.com/api/character/?page="+str(i)+"&species="+especie
    data = requests.get(URL) 
    data = data.json() #convertimos la respuesta en dict
    for j in data['results']:
        print(j['name']+" "+['species'])
