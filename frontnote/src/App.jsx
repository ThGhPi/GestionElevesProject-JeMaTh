import { useState } from 'react'
import { BrowserRouter, Routes, Route } from 'react-router';
import Header from './layout/Header';
import LoginView from './view/LoginView';
import UserList from './view/admin-view/UserList';
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path='/connexion' element={<LoginView />} />
          <Route path='/liste-utilisateurs' element={<UserList />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
