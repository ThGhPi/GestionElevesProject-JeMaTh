import React from 'react'

const Button = (props) => {
  return (
    <button className="w-full py-2 rounded-lg transition" {...props}>
      {props.children}
    </button>
  )
}

export default Button