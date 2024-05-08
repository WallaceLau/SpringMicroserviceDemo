var app = new Vue({
    el: '#app',
    data: {
        products: [],
        product_service: 'localhost:8080',
        order_service: 'localhost:8082',
    },
    created() {
        this.fetchProducts();
    },
    methods: {
        fetchProducts() {
            fetch('http://'+this.product_service+'/product/getAllProduct', {
                method: 'GET',
                mode: 'cors', // This is the mode you can specify for handling CORS
                headers: {
                    'Content-Type': 'application/json',
                    // You can add client-side headers here, but not CORS headers
                },
            })
            .then(response => response.json())
            .then(data => {
                this.products = data.map(product => ({
                    ...product,
                    isAddedToCart: false
                    
                }));
            })
            .catch(error => console.error('Error:', error));
        },
        addToCart(product) {
            product.isAddedToCart = true;
            // Additional logic for adding to cart can be implemented here
        },
        placeOrder() {
            const orderDetails = {
                productId: 1, 
                totalAmount: 1000, 
                quantity: 1,
                PaymentMode: "CASH"
            };

            fetch('http://'+this.order_service+'/order/placeOrder', {
                method: 'POST',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(orderDetails)
            })
            .then(response => {
                if (response.status === 200) {
                    // Show custom alert
                    const alertBox = document.getElementById('customAlert');
                    alertBox.style.display = 'block';

                    // Hide the alert after 0.5 seconds
                    setTimeout(() => {
                        alertBox.style.display = 'none';
                    }, 1000);

                    return response.json();
                } else {
                    throw new Error('Order not placed');
                }
            })
            .then(data => {
                console.log('Order details:', data);
                // Additional logic after order is placed
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to place order: ' + error.message);
            });
        }
    }
});
