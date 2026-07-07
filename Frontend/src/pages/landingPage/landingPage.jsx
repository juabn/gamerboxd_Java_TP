import './landingPage.css';
import { Link } from 'react-router-dom';
// eslint-disable-next-line no-unused-vars
import {useState,useEffect} from 'react';

// Importamos los componentes de React de Swiper
import { Swiper, SwiperSlide } from 'swiper/react';

// Importamos los estilos base de Swiper
import 'swiper/css';
import 'swiper/css/effect-coverflow';
import 'swiper/css/pagination';
import { EffectCoverflow, Pagination } from 'swiper/modules';
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
			  <div className='highlightedReviews'  >
			  <Swiper
			      effect={'coverflow'}
			      grabCursor={true}
			      centeredSlides={true}
			      slidesPerView={'auto'} // Deja que el CSS dicte el ancho de la tarjeta
			      initialSlide={1} // Empieza en la segunda tarjeta (para que quede centrada)
			      coverflowEffect={{
			        rotate: 0,       // Tarjetas rectas (sin rotación extraña)
			        stretch: 0,      // Espacio entre tarjetas
			        depth: 150,      // Qué tan atrás se van las tarjetas de los costados
			        modifier: 2.5,   // Multiplicador del efecto
			        slideShadows: false, // Apagamos las sombras por defecto porque ya tenés las tuyas
			      }}
			      pagination={{ clickable: true }}
			      modules={[EffectCoverflow, Pagination]}
			      className="mi-carrusel-reviews"
			    >
			      {resenas.map((resenia, index) => (
			        // En Swiper, cada elemento mapeado DEBE estar envuelto en un SwiperSlide
			        <SwiperSlide key={index}>
			          <ReviewLandCard 
			            titulo={resenia.titulo} 
			            descripcion={resenia.descripcion} 
			            puntaje={resenia.puntaje}
			            nombreJuego="Stardew Valley" 
			            nombreUsuario="Gamer123"
			            fotoJuego={resenia.fotoJuego} // O la variable que uses para tu banner
			          />
			        </SwiperSlide>
			      ))}
			    </Swiper>
	            </div>
          </section>
  );
}
