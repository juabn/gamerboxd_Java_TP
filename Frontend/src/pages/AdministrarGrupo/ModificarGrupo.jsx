import { useState } from 'react';
import { useNavigate } from 'react-router';
import { useLocation } from 'react-router-dom';

function ModificarGrupo() {
    const location = useLocation();
    const navigate = useNavigate();

    const idgrupo = location.state?.id || '';

    // Estados para los datos visibles actuales
    const [fotoActual, setFotoActual] = useState(location.state?.img || '');
    const [nombreActual, setNombreActual] = useState(location.state?.nombre || '');

    // Estados para los inputs del formulario
    const [nuevonombre, setnuevonombre] = useState("");
    const [descripcion, setdescripcion] = useState("");
    const [nuevaImagen, setNuevaImagen] = useState("");
    const [estadofoto, setestadofoto] = useState(false);

    const insertarnombre = (e) => setnuevonombre(e.target.value);
    const insertardescripcion = (e) => setdescripcion(e.target.value);

    const handleLogout = () => {
        navigate("/");
    };

    const dardebaja = (e) => {
        let token = localStorage.getItem('token');
        e.preventDefault();

        fetch('http://localhost:8081/dardebajagrupo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({ id: idgrupo })
        })
        .then(response => {
            if (response.status === 200) {
                alert("Grupo dado de baja correctamente");
                handleLogout();
            } else if (response.status === 401) {
                alert("Error en la bd, intente nuevamente más tarde");
            }
        })
        .catch(() => {
            alert("Error inesperado, intente nuevamente más tarde");
        });
    };

    const enviar = (e) => {
        let token = localStorage.getItem('token');
        e.preventDefault();

        fetch('http://localhost:8081/actualizardatosgrupo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({
                id: idgrupo,
                foto_perfil: nuevaImagen,
                nombre: nuevonombre,
                descripcion: descripcion
            })
        })
        .then(response => {
            if (response.status === 200) {
                alert("Actualización realizada con éxito");

                // Actualizamos la vista inmediatamente si se enviaron cambios
                if (nuevonombre.trim() !== "") {
                    setNombreActual(nuevonombre);
                    setnuevonombre("");
                }
                if (nuevaImagen !== "") {
                    setFotoActual(nuevaImagen);
                    setNuevaImagen("");
                    setestadofoto(false);
                }
                setdescripcion("");
            } else if (response.status === 409) {
                alert("Ya existe grupo con ese nombre");
            } else {
                alert("Error en la conexión, pruebe más tarde");
            }
        })
        .catch(error => {
            console.error("El error real es:", error);
            alert('Error en la conexión con la base de datos, pruebe más tarde');
        });
    };

    const volver = () => {
        navigate("/login");
    };

    const insertarimagen = (e) => {
        if (e.target.files && e.target.files[0]) {
            let reader = new FileReader();
            reader.readAsDataURL(e.target.files[0]);
            reader.onload = () => {
                setNuevaImagen(reader.result);
                setestadofoto(true);
            };
        }
    };

    return (
        <div className="contendorprincipal">
            <form onSubmit={enviar} className='form'>
                <p className='text'>Actualizar imagen</p>

                <input
                    className='file-input'
                    type="file"
                    accept="image/*"
                    onChange={insertarimagen}
                />

                {/* Muestra la previsualización si seleccionó una nueva, o la actual si no */}
                <img
                    className='imagen'
                    style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }}
                    src={nuevaImagen || fotoActual}
                    alt="Foto del grupo"
                />

                {/* Muestra el estado nombreActual */}
                <p className='text'>{nombreActual}</p>

                <p className='text'>Cambiar nombre del grupo</p>
                <input
                    className='input'
                    placeholder="Ingrese nuevo nombre"
                    value={nuevonombre}
                    onChange={insertarnombre}
                />

                <p className='text'>Cambiar descripción</p>
                <input
                    className='input'
                    placeholder="Ingrese nueva descripción"
                    value={descripcion}
                    onChange={insertardescripcion}
                />

                <button
                    className='submit-btn'
                    type="submit"
                    disabled={!estadofoto && nuevonombre.trim() === "" && descripcion.trim() === ""}
                >
                    Confirmar cambios
                </button>
                <button type="button" onClick={dardebaja}>Dar de baja grupo</button>
                <button type="button" onClick={volver}>Volver</button>
            </form>
        </div>
    );
}

export default ModificarGrupo;