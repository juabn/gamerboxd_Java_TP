import './landingPage.css';
// eslint-disable-next-line no-unused-vars
import {useState,useEffect} from 'react';
import { useNavigate } from "react-router";

import Carrousel from '../../components/carrousel/Carrousel';
import banner1 from '../../assets/juego1.jpg';
import banner2 from '../../assets/juego2.jpg';
import banner3 from '../../assets/juego3.jpg';
import banner4 from '../../assets/juego4.jpg';
import banner5 from '../../assets/juego5.jpg';

export default function LandingPage() {
	
	const navigate = useNavigate();
	
	
	const iralogin = () => {
		
		navigate("/login")
	}
	const imagenesCarrousel = [banner1,banner2,banner3,banner4,banner5];
  return (
	
    <section className="header">
	
		<div className='headerDiv'>
			<h1 className='headerDivTitle'>Reseña tus juegos favoritos</h1>
			<button onClick={iralogin}> iniciar sesion </button>
			
		</div>
		<Carrousel imagenes={imagenesCarrousel} />
    </section>
  );
}
