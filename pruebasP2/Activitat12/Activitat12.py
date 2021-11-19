# adds image processing capabilities
from PIL import Image
# will convert the image to text string
import pytesseract

img = Image.open('foto.png')

result = pytesseract.image_to_string(img)

with open("text_result.txt", mode ="w") as file:
    file.write(result)
