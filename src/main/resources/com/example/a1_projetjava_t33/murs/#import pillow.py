
from PIL import Image

img = Image.open('Bordureh.png')

for a in range(1,17):
    img.save('q'+str(a)+'.png')
for a in range(1,17):
    img.save('z'+str(a)+'.png')