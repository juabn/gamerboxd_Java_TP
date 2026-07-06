import './landingPage.css';
import { Link } from 'react-router-dom';
// eslint-disable-next-line no-unused-vars
import {useState,useEffect} from 'react';


import Carrousel from '../../components/carrousel/Carrousel';
import Navbar from '../../components/Navbar/Navbar'
import banner1 from '../../assets/juego1.jpg';
import banner2 from '../../assets/juego2.jpg';
import banner3 from '../../assets/juego3.jpg';
import banner4 from '../../assets/juego4.jpg';
import banner5 from '../../assets/juego5.jpg';
import ReviewLandCard from '../../components/reviewLandCard/reviewLandCard';

export default function LandingPage() {
	

	const imagenesCarrousel = [banner1,banner2,banner3,banner4,banner5];
	
	const [resenas, setReseñas] = useState([]);

	  // 2. Simulamos la llamada a tu API/backend
	  useEffect(() => {
	    // Reemplazá esta URL con el endpoint real de tu backend
	    fetch('http://localhost:8080/api/reseñas') 
	      .then(respuesta => respuesta.json())
	      .then(datos => {
	        setReseñas(datos);
	      })
	      .catch(error => console.error("Error al cargar la base de datos:", error));
	      
	      /* Si aún no tenés el backend listo, podés comentar el fetch de arriba 
	         y usar datos de prueba (mock) así:
	      
	      setReseñas([
	        { id: 1, fotoJuego: 'ruta/foto1.jpg', tituloResenia: 'Obra maestra', puntaje: 10 },
	        { id: 2, fotoJuego: 'ruta/foto2.jpg', tituloResenia: 'Muy repetitivo', puntaje: 6 },
	        { id: 3, fotoJuego: 'ruta/foto3.jpg', tituloResenia: 'Buen multijugador', puntaje: 8 },
	      ]);
	      */
	  }, []);
  return (
	  <section className="body">
	  	<Navbar class="nav"></Navbar>
              <div className="headerContainer">
                  <div className='headerDiv'>
                      <h1 className='headerDivTitle'>Reseña tus juegos favoritos</h1>
                      <Link to='/login'>
                          <button className="botonLogin">Pruebalo ahora</button>
                      </Link>

                  </div>
                  <div className="cFilter">
                      <Carrousel imagenes={imagenesCarrousel} className="cAnim" />
                  </div>
              </div>
			  <div className='highlightedReviews' style={{ display: 'flex', overflowX: 'auto', gap: '16px', padding: '20px' }}>
			                {resenas.map((resenia) => (
			                    <ReviewLandCard 
			                        key={resenia.id} 
			                        fotoJuego={resenia.fotoJuego} 
			                        tituloResenia={resenia.tituloResenia} 
			                        puntaje={resenia.puntaje} 
			                    />
			                ))}
			            </div>
          </section>
  );
}
