import { useState, useEffect } from 'react';
import './carrousel.css';



export default function Carrousel({ imagenes }) {
	
  const [currentIndex, setCurrentIndex] = useState(0);

  const TabContainer() => {
	const [isPending, startTransition] = useTransition();
	const [tab, setTab] = useState('about');
  }
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
          className="carrousel-img" 
        />
      </div>



      <div className="carrousel-dots">
        {imagenes.map((_, index) => (
          <span
            key={index}
            className={`dot ${index === currentIndex ? 'active' : ''}`}
          />
        ))}
      </div>
    </div>
  );
}