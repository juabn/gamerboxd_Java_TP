import { useState, useEffect } from 'react';
import './Carrousel.css';



export default function Carrousel({ imagenes }) {
	
  const [currentIndex, setCurrentIndex] = useState(0);
  
  const [animacionClase, setAnimacionClase] = useState(false);
  
  useEffect(()=>{
	const animacionId = setInterval(()=> {
		setAnimacionClase((prev)=> !prev);
	}, 1500);
	return() => {
		clearInterval(animacionId)
	}
  }, []);


  const siguienteImagen = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === imagenes.length - 1 ? 0 : prevIndex + 1
    );
  };
	
  useEffect(() => {
	
	
	
      const temporizador = setInterval(() => {
        siguienteImagen();
      }, 5000);
      return () => clearInterval(temporizador);
      
    }, [currentIndex, imagenes.length]);

  return (
    <div className="carrousel-contenedor">

      <div className="carorusel-slide">
        <img 
          src={imagenes[currentIndex]} 
          alt={`Slide ${currentIndex + 1}`} 
          className={animacionClase ? "fade-out" : "fade-in"} 
        />
      </div>
    </div>
  );
}