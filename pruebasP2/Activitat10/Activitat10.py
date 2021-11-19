from glob import glob
import cv2
from pyzbar.pyzbar import decode
  
def BarcodeReader(image): #Crear la funcion BarcodeReader
     
    # Leer la imagen con cv2
    img = cv2.imread(image)
    #Separar nombre de extension para conseguir el nombre de la persona
    name = image.split(".")[0]
    # Decodificar la imagen
    detectedBarcodes = decode(img)
    # Si no se detecta resultado se printea este mensaje
    if not detectedBarcodes:
        print("ERROR: No se ha detectado codigo de barras")
    else:
        # Recorrer el codigo de barras
        for barcode in detectedBarcodes: 
           
            # Localizar su posicion en la imagen
            (x, y, w, h) = barcode.rect
             
            # Poner rectangulo en la imagen
            # cv2 to heighlight the barcode
            cv2.rectangle(img, (x-10, y-10),
                          (x + w+10, y + h+10),
                          (255, 0, 0), 2)
             
            if barcode.data!="":
               
            # Printear los datos del codigo de barras
                print("Nombre: "+str(name) + " | Codigo: "+str(barcode.data).replace("b","").replace("'","")+" | Tipo: "+str(barcode.type))
        
    #Para mostrar la imagen descomentar lo siguiente
    #cv2.imshow("Image", img)
    #cv2.waitKey(0)
    #cv2.destroyAllWindows()
 
if __name__ == "__main__":
#Buscar en la carpeta actual todos los archivos, si hay un .jpeg, se analiza para sacar el codigo
    archivos = glob("*")
    for i in archivos:
      if i.endswith("jpeg"):
        BarcodeReader(i)