import React from 'react'

const Label = ({children}) => {
  return (
    <>
      <label className="block text-sm font-medium text-gray-600 mb-1">
        {children}
      </label>
    </>
  )
}

export default Label