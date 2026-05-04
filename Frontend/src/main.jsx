import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'

import Hola from './pages/Register/Register.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Hola />
  </StrictMode>,
)
