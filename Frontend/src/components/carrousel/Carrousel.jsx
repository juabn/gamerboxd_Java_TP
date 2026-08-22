import { useState, useEffect, useRef } from 'react';
import './Carrousel.css';




export default function Carrousel({ imagenes }) {
	
  const [currentIndex, setCurrentIndex] = useState(0);
  const imagenRef = useRef(null);



  const siguienteImagen = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === imagenes.length - 1 ? 0 : prevIndex + 1
    );
  };
	
  useEffect(() => {
      const temporizador = setInterval(siguienteImagen, 7000);
      return () => clearInterval(temporizador);
    }, [currentIndex]);

	useEffect(() => {
	    const imgElement = imagenRef.current;
	    
	    if (imgElement) {
	      imgElement.classList.remove('fade-in');
	      
	      void imgElement.offsetWidth;
	      
	      imgElement.classList.add('fade-in');
	    }
	  }, [currentIndex]); 

  return (
	<div className="carrusel-contenedor">
	      <div className="carrusel-slide">
	        <img 
	          ref={imagenRef}
	          src={imagenes[currentIndex]} 
	          alt={`Slide ${currentIndex + 1}`} 
	          className="carrusel-img" 
	        />
	      </div>

	    </div>
  );
}