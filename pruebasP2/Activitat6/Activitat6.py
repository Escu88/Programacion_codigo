from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
#Creacion del engine para conectarnos a la bd
engine = create_engine('sqlite:///escoles.db')
#Se inicia la sesion
Session = sessionmaker(bind=engine)
session = Session()

Base = declarative_base()