import { useState } from 'react'
import { BrowserRouter, Routes, Route } from 'react-router';
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path='/connexion' element={<LoginView />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
