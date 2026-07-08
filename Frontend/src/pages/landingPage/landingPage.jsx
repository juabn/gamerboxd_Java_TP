import './landingPage.css';
import { Link } from 'react-router-dom';
// eslint-disable-next-line no-unused-vars
import {useState,useEffect} from 'react';


import Carrousel from '../../components/carrousel/Carrousel';

import banner1 from '../../assets/juego1.jpg';
import banner2 from '../../assets/juego2.jpg';
import banner3 from '../../assets/juego3.jpg';
import banner4 from '../../assets/juego4.jpg';
import banner5 from '../../assets/juego5.jpg';

export default function LandingPage() {
	

	const imagenesCarrousel = [banner1,banner2,banner3,banner4,banner5];
  return (
	  <section className="body">
	  	
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
          </section>
  );
}
