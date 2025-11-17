import React from 'react'

const Input = (props) => {
  return (
    <>
      <input className="w-full p-2 border rounded-lg focus:outline-none
        focus:ring focus:ring-blue-200"
        {...props} />
    </>
  )
}

export default Input