// For Add Item to Cart
export const addCart = (product) =>{
    return {
        type:"ADDTOCART",
        payload:product
    }
}

// For Delete Item to Cart
export const delCart = (product) =>{
    return {
        type:"REMOVEFROMCART",
        payload:product
    }
}