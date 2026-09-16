import './crearAdministrador.css';
import { useState } from 'react';
import { API_URL } from '../../config';
import Footer from '../../components/Footer/Footer';
import AlertMessage from '../../components/AlertMessage/AlertMessage';

function CreacionAdmin(){
	
	const [mail, setmail] = useState("");
    const [alerta, setAlerta] = useState(null);
	
	const handlemail = (e) => {
		setmail(e.target.value);
	}
	
	const enviar = (e) => {
		e.preventDefault();
		let token = localStorage.getItem('token');
		
        if (!mail.trim()) {
            setAlerta({ tipo: 'error', mensaje: "Por favor, ingresa un correo electronico." });
            setTimeout(() => setAlerta(null), 4000);
            return;
        }
				
		fetch(`${API_URL}/convertirenadmin`,{
			method: 'POST', 
			headers: {
			    'Content-Type': 'application/json',
			    'Authorization': 'Bearer ' + token 
            },
			body: JSON.stringify({mail: mail}) 
		})
		.then(response => {
			if(response.status === 200){
                setAlerta({ tipo: 'ok', mensaje: "Usuario convertido en administrador exitosamente." });
				setmail("");
                setTimeout(() => setAlerta(null), 4000);
			}
			else if (response.status === 405){
                setAlerta({ tipo: 'error', mensaje: "No existe un usuario con ese correo." });
                setTimeout(() => setAlerta(null), 4000);
			}
			else if (response.status === 401){
                setAlerta({ tipo: 'error', mensaje: "Este usuario se encuentra inactivo. Cambia su estado para convertirlo en administrador." });
                setTimeout(() => setAlerta(null), 5000);
			}
			else if (response.status === 402){
                setAlerta({ tipo: 'error', mensaje: "Este usuario ya posee permisos de administrador." });
                setTimeout(() => setAlerta(null), 4000);
			}
			else{
                setAlerta({ tipo: 'error', mensaje: "Error en la base de datos, intente nuevamente mas tarde." });
                setTimeout(() => setAlerta(null), 4000);
			}
        })
        .catch(() => {
            setAlerta({ tipo: 'error', mensaje: "Error inesperado de conexion, intente nuevamente mas tarde." });
            setTimeout(() => setAlerta(null), 4000);
        });
	}
	
	return(
        <section className="pagina-crear-admin">
            <div className="crear-admin-container">
                
                <div className="crear-admin-card-glass">
                    <div className="crear-admin-header">
                        <h2 className="crear-admin-titulo">Crear Administrador</h2>
                        <p className="crear-admin-subtitulo">Otorga permisos especiales a un usuario de la plataforma.</p>
                    </div>

                    <form className="crear-admin-form" onSubmit={enviar}>
                        
                        <div className="crear-admin-campo">
                            <label className="crear-admin-label">Correo electronico del usuario</label>
                            <input
                                className="crear-admin-input"
                                type="email"
                                value={mail}
                                onChange={handlemail}
                                placeholder="Ej: usuario@gamerboxd.com"
                                required
                            />
                        </div>
                        
                        <div className="crear-admin-acciones">
                            <button className="btn-primario" type="submit">
                                Convertir en administrador
                            </button>
                        </div>
                    </form>
                </div>

                
                {alerta !== null && (
                    <div className="crear-admin-alerta-wrapper">
                        <AlertMessage 
                            tipo={alerta.tipo} 
                            mensaje={alerta.mensaje} 
                            onClose={() => setAlerta(null)} 
                        />
                    </div>
                )}

            </div>
            
            <Footer />
        </section>
	)
}

export default CreacionAdmin;