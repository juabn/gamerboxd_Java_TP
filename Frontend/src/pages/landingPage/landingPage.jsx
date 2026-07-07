import './landingPage.css';
import { Link } from 'react-router-dom';
// eslint-disable-next-line no-unused-vars
import {useState,useEffect} from 'react';
import { register } from 'swiper/element/bundle';
register();


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
	
	const [resenas, setResenas] = useState([]);

	  useEffect(() => {
	    fetch('http://localhost:8081/allresenias') 
	      .then(respuesta => respuesta.json())
	      .then(datos => {
			console.log("Datos crudos del servidor:", datos);
			const juegosVistos = new Set();
			        const resenasJuegosUnicos = datos.filter(resenia => {
			          
			          const id = resenia.id_juego;
					  if (!juegosVistos.has(id)) {
					              juegosVistos.add(id);
					              return true;
					            }
						return false;
						});
						
					
				const resenasMezcladas = resenasJuegosUnicos.sort(()=> 0.5 - Math.random());
				const top5Aleatorias = resenasMezcladas.slice(0, 5);
				
				setResenas(top5Aleatorias);
				
			})
	      .catch(error => console.error("Error conexion:", error));
	      
	    
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
			  <div className='highlightedReviews'>
			    <swiper-container
				className='swiper-container-highlighted-r' 
				  effect="coverflow"
				  coverflow-effect-rotate="0"     
				    coverflow-effect-stretch="-90"   
				    coverflow-effect-depth="150"     
				    coverflow-effect-modifier="1"    
				    coverflow-effect-slide-shadows="false" 
				    grab-cursor="false" 
				    centered-slides="true"
				    slides-per-view="1" 
				    navigation="true" 
				    loop="true"
			      
			    >
			      {resenas.map((resenia, index) => (
			        <swiper-slide key={index}>
			          <ReviewLandCard 
			            titulo={resenia.titulo} 
			            descripcion={resenia.descripcion} 
			            puntaje={resenia.puntaje}
			            nombreJuego="Stardew Valley" 
			            nombreUsuario="Gamer123"
			            fotoJuego={banner3} 
			          />
			        </swiper-slide>
			      ))}
			    </swiper-container>
			  </div>
          </section>
  );
}
