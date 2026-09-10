import './ListadoGrupos.css';
import GruposCard from '../../components/GruposCard/GruposCard';
import Footer from '../../components/Footer/Footer';
import AlertMessage from '../../components/AlertMessage/AlertMessage'; 
import { useState, useEffect } from 'react';
import { API_URL } from '../../config';

function Propuestas(){
	
	const [listaGrupos, setListaGrupos] = useState([]);
    const [alerta, setAlerta] = useState(null); 
	
	useEffect(() => {
	    let token = localStorage.getItem('token');
	
	    fetch(`${API_URL}/listarGrupos`,{
				method: 'POST', 
				headers: {
				    'Authorization': 'Bearer ' + token 
                }			
		})
	    .then(response => response.json())
	    .then(data => {
		    setListaGrupos(data);
	    })
	    .catch(error => console.error('Error:', error));

	}, []);
	
	return(
        <section className="pagina-listado-grupos">
            
            
		<div className='divprincipallistadogrupos'>
		                
		                <div className="listadogrupos-header">
		                    <h2 className="listadogrupos-titulo">Grupos existentes</h2>
		                    <p className="listadogrupos-subtitulo">Explorá los grupos disponibles y unite para jugar.</p>
		                    
		                    
		                    {alerta !== null && (
		                        <div className="listadogrupos-alerta-wrapper">
		                            <AlertMessage 
		                                tipo={alerta.tipo} 
		                                mensaje={alerta.mensaje} 
		                                onClose={() => setAlerta(null)} 
		                            />
		                        </div>
		                    )}
                </div>
                <div className="listadogrupos-grid">
                    {listaGrupos.map((grupo) => (
                        <GruposCard
                            key={grupo.id} 
                            gru={{
                                id: grupo.id,
                                name: grupo.nombre,
                                descripcion: grupo.descripcion,
                                imagen: grupo.foto_perfil,
                                cantidadIntegrantes: grupo.cantidadIntegrantes
                            }} 
                            
                            onMostrarAlerta={setAlerta}
                        />
                    ))}
                </div>
                
            </div>
            
            <Footer />
        </section>
	);
}

export default Propuestas;