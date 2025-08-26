<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KidZone - Sự Kiện & Cửa Hàng Cho Trẻ Em</title>
    <script src="https://cdn.tailwindcss.com/3.4.16"></script>
    <script>tailwind.config={theme:{extend:{colors:{primary:'#FF6B6B',secondary:'#4ECDC4'},borderRadius:{'none':'0px','sm':'4px',DEFAULT:'8px','md':'12px','lg':'16px','xl':'20px','2xl':'24px','3xl':'32px','full':'9999px','button':'8px'}}}}</script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.6.0/remixicon.min.css">
    <style>
        :where([class^="ri-"])::before { content: "\f3c2"; }
        body {
            font-family: 'Quicksand', sans-serif;
        }
        .event-card:hover, .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }
        input[type="number"]::-webkit-inner-spin-button,
        input[type="number"]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        .custom-checkbox {
            position: relative;
            padding-left: 30px;
            cursor: pointer;
            user-select: none;
        }
        .custom-checkbox input {
            position: absolute;
            opacity: 0;
            cursor: pointer;
            height: 0;
            width: 0;
        }
        .checkmark {
            position: absolute;
            top: 0;
            left: 0;
            height: 20px;
            width: 20px;
            background-color: #fff;
            border: 2px solid #ddd;
            border-radius: 4px;
        }
        .custom-checkbox:hover input ~ .checkmark {
            border-color: #FF6B6B;
        }
        .custom-checkbox input:checked ~ .checkmark {
            background-color: #FF6B6B;
            border-color: #FF6B6B;
        }
        .checkmark:after {
            content: "";
            position: absolute;
            display: none;
        }
        .custom-checkbox input:checked ~ .checkmark:after {
            display: block;
        }
        .custom-checkbox .checkmark:after {
            left: 6px;
            top: 2px;
            width: 5px;
            height: 10px;
            border: solid white;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
    </style>
</head>
<body class="bg-white min-h-screen">
<!-- Header Section -->
<header class="sticky top-0 z-50 bg-white shadow-md">
    <div class="container mx-auto px-4 py-3 flex items-center justify-between">
        <div class="flex items-center">
            <a href="#" class="text-3xl font-['Pacifico'] text-primary">logo</a>
            <nav class="hidden md:flex ml-10">
                <ul class="flex space-x-8">
                    <li><a href="#events" class="font-medium text-gray-700 hover:text-primary transition-colors">Events</a></li>
                    <li><a href="#shop" class="font-medium text-gray-700 hover:text-primary transition-colors">Shop</a></li>
                    <li><a href="#" class="font-medium text-gray-700 hover:text-primary transition-colors">About Us</a></li>
                    <li><a href="#" class="font-medium text-gray-700 hover:text-primary transition-colors">Contact</a></li>
                </ul>
            </nav>
        </div>
        <div class="flex items-center space-x-4">
            <div class="relative hidden md:block">
                <input type="text" placeholder="Search..." class="pl-10 pr-4 py-2 rounded-full text-sm border border-gray-300 focus:outline-none focus:border-primary w-40 lg:w-64">
                <div class="absolute left-3 top-2.5 w-5 h-5 flex items-center justify-center text-gray-400">
                    <i class="ri-search-line"></i>
                </div>
            </div>
            <div class="w-10 h-10 flex items-center justify-center relative cursor-pointer">
                <i class="ri-shopping-cart-2-line text-xl text-gray-700 hover:text-primary transition-colors"></i>
                <span class="absolute -top-1 -right-1 bg-primary text-white text-xs rounded-full w-5 h-5 flex items-center justify-center">3</span>
            </div>
            <div class="w-10 h-10 flex items-center justify-center cursor-pointer">
                <i class="ri-user-smile-line text-xl text-gray-700 hover:text-primary transition-colors"></i>
            </div>
            <div class="md:hidden w-10 h-10 flex items-center justify-center cursor-pointer">
                <i class="ri-menu-line text-xl"></i>
            </div>
        </div>
    </div>
</header>
<!-- Hero Banner -->
<section class="relative bg-gradient-to-r from-[#FFF8F8] to-[#F9FDFF] overflow-hidden" style="background-image: url('https://readdy.ai/api/search-image?query=A%20playful%20and%20vibrant%20children%5Cs%20playground%20scene%20with%20colorful%20toys%2C%20balloons%2C%20and%20educational%20elements%20scattered%20around.%20The%20left%20side%20has%20a%20clean%2C%20light%20gradient%20background%20transitioning%20smoothly%20to%20the%20right%20side%20which%20shows%20happy%20children%20playing%20with%20toys.%20Soft%20pastel%20colors%2C%20child-friendly%20atmosphere%2C%20professional%20photography%20style%2C%20bright%20and%20cheerful.&width=1600&height=600&seq=hero123&orientation=landscape'); background-position: right center; background-repeat: no-repeat; background-size: contain;">
    <div class="container mx-auto px-4 py-16 md:py-24 w-full">
        <div class="flex flex-col md:flex-row items-center">
            <div class="w-full md:w-1/2 text-left mb-10 md:mb-0">
                <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-800 mb-6">Nơi Vui Chơi Gặp Gỡ Học Tập Cho Bé Yêu</h1>
                <p class="text-lg text-gray-600 mb-8 max-w-lg">Tham gia thế giới diệu kỳ của chúng tôi với các sự kiện sáng tạo và sản phẩm chất lượng được thiết kế đặc biệt cho sự phát triển và niềm vui của trẻ.</p>
                <div class="flex flex-wrap gap-4">
                    <a href="#events" class="px-6 py-3 bg-primary text-white font-semibold rounded-button shadow-lg hover:bg-opacity-90 transition-all whitespace-nowrap">Đặt Sự Kiện</a>
                    <a href="#shop" class="px-6 py-3 bg-white text-primary font-semibold rounded-button border-2 border-primary hover:bg-gray-50 transition-all whitespace-nowrap">Mua Sắm Ngay</a>
                </div>
            </div>
            <div class="w-full md:w-1/2">
                <!-- Space for the background image -->
            </div>
        </div>
    </div>
</section>
<!-- Events Section -->
<section id="events" class="py-16 bg-white">
    <div class="container mx-auto px-4">
        <div class="text-center mb-12">
            <h2 class="text-3xl md:text-4xl font-bold text-gray-800 mb-4">Sự Kiện Sắp Diễn Ra</h2>
            <p class="text-gray-600 max-w-2xl mx-auto">Khám phá các sự kiện được thiết kế tỉ mỉ để khơi dậy sự sáng tạo, học tập và niềm vui cho trẻ em ở mọi lứa tuổi.</p>
        </div>
        <div class="mb-8 flex flex-wrap items-center justify-between gap-4">
            <div class="flex flex-wrap gap-2">
                <div class="bg-gray-100 rounded-full px-1 py-1 inline-flex">
                    <button class="px-4 py-1.5 rounded-full bg-primary text-white text-sm font-medium whitespace-nowrap">Mọi Lứa Tuổi</button>
                    <button class="px-4 py-1.5 rounded-full text-gray-700 text-sm font-medium hover:bg-gray-200 whitespace-nowrap">0-3 Tuổi</button>
                    <button class="px-4 py-1.5 rounded-full text-gray-700 text-sm font-medium hover:bg-gray-200 whitespace-nowrap">4-7 Tuổi</button>
                    <button class="px-4 py-1.5 rounded-full text-gray-700 text-sm font-medium hover:bg-gray-200 whitespace-nowrap">8-12 Tuổi</button>
                </div>
            </div>
            <div class="flex items-center gap-2">
                <button class="px-4 py-2 rounded-button border border-gray-300 text-gray-700 flex items-center gap-2 hover:bg-gray-50 whitespace-nowrap">
                    <i class="ri-calendar-line"></i> Xem Lịch
                </button>
                <button class="px-4 py-2 rounded-button border border-gray-300 text-gray-700 flex items-center gap-2 bg-gray-100 whitespace-nowrap">
                    <i class="ri-layout-grid-line"></i> Xem Lưới
                </button>
            </div>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            <!-- Event Card 1 -->
            <div class="event-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                <div class="h-48 overflow-hidden">
                    <img src="https://readdy.ai/api/search-image?query=Children%20engaged%20in%20a%20colorful%20art%20workshop%20with%20paint%2C%20brushes%2C%20and%20canvases.%20Bright%2C%20well-lit%20classroom%20setting%20with%20art%20supplies%20scattered%20on%20tables.%20Children%20of%20various%20ages%20wearing%20aprons%2C%20focused%20on%20their%20creative%20projects.%20Clean%2C%20professional%20photography%20with%20vibrant%20colors%20showing%20joy%20and%20creativity.&width=600&height=400&seq=event1&orientation=landscape" alt="Art Workshop" class="w-full h-full object-cover object-top">
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-3">
                        <h3 class="text-xl font-bold text-gray-800">Hội Thảo Nghệ Thuật Sáng Tạo</h3>
                        <span class="bg-blue-100 text-blue-800 text-xs font-medium px-2.5 py-1 rounded">4-7 Tuổi</span>
                    </div>
                    <div class="flex items-center text-gray-500 mb-3 text-sm">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-calendar-event-line"></i>
                        </div>
                        30/05/2025 • 10:00 - 12:00
                    </div>
                    <p class="text-gray-600 mb-4 text-sm">Trẻ em sẽ khám phá các kỹ thuật nghệ thuật khác nhau và tạo ra những tác phẩm của riêng mình để mang về nhà.</p>
                    <button class="w-full py-2.5 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Đặt Ngay</button>
                </div>
            </div>
            <!-- Event Card 2 -->
            <div class="event-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                <div class="h-48 overflow-hidden">
                    <img src="https://readdy.ai/api/search-image?query=Children%20in%20a%20science%20workshop%20setting%20with%20small%20experiments%2C%20test%20tubes%2C%20and%20microscopes.%20Kids%20wearing%20safety%20goggles%2C%20engaged%20in%20hands-on%20scientific%20discovery.%20Bright%20classroom%20environment%20with%20educational%20posters%20on%20walls.%20Professional%20photography%20showing%20excitement%20and%20curiosity%20on%20childrens%20faces%20as%20they%20learn.&width=600&height=400&seq=event2&orientation=landscape" alt="Science Workshop" class="w-full h-full object-cover object-top">
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-3">
                        <h3 class="text-xl font-bold text-gray-800">Phòng Thí Nghiệm Nhà Khoa Học Nhí</h3>
                        <span class="bg-green-100 text-green-800 text-xs font-medium px-2.5 py-1 rounded">8-12 Tuổi</span>
                    </div>
                    <div class="flex items-center text-gray-500 mb-3 text-sm">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-calendar-event-line"></i>
                        </div>
                        02/06/2025 • 14:00 - 16:00
                    </div>
                    <p class="text-gray-600 mb-4 text-sm">Các thí nghiệm thực hành thú vị giúp khoa học trở nên vui nhộn và dễ tiếp cận với những tâm hồn trẻ thơ tò mò.</p>
                    <button class="w-full py-2.5 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Đặt Ngay</button>
                </div>
            </div>
            <!-- Event Card 3 -->
            <div class="event-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                <div class="h-48 overflow-hidden">
                    <img src="https://readdy.ai/api/search-image?query=Toddlers%20and%20babies%20in%20a%20sensory%20play%20session%20with%20soft%20toys%2C%20textured%20objects%2C%20and%20colorful%20play%20mats.%20Bright%2C%20clean%20nursery%20environment%20with%20parents%20sitting%20nearby.%20Professional%20photography%20showing%20babies%20exploring%20different%20textures%20and%20objects%20with%20curiosity.%20Soft%20lighting%20and%20pastel%20colors%20creating%20a%20nurturing%20atmosphere.&width=600&height=400&seq=event3&orientation=landscape" alt="Baby Sensory Play" class="w-full h-full object-cover object-top">
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-3">
                        <h3 class="text-xl font-bold text-gray-800">Vui Chơi Cảm Giác Cho Bé</h3>
                        <span class="bg-purple-100 text-purple-800 text-xs font-medium px-2.5 py-1 rounded">0-3 Tuổi</span>
                    </div>
                    <div class="flex items-center text-gray-500 mb-3 text-sm">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-calendar-event-line"></i>
                        </div>
                        05/06/2025 • 09:00 - 10:30
                    </div>
                    <p class="text-gray-600 mb-4 text-sm">Giới thiệu nhẹ nhàng về các kết cấu, âm thanh và chuyển động được thiết kế đặc biệt cho các nhà thám hiểm nhỏ tuổi nhất của chúng ta.</p>
                    <button class="w-full py-2.5 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Đặt Ngay</button>
                </div>
            </div>
            <!-- Event Card 4 -->
            <div class="event-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                <div class="h-48 overflow-hidden">
                    <img src="https://readdy.ai/api/search-image?query=Children%20participating%20in%20a%20cooking%20class%2C%20wearing%20chef%20hats%20and%20aprons.%20Kids%20mixing%20ingredients%20in%20bowls%2C%20decorating%20cookies%2C%20and%20learning%20basic%20cooking%20skills.%20Bright%2C%20clean%20kitchen%20environment%20with%20child-safe%20utensils.%20Professional%20photography%20showing%20childrens%20excitement%20and%20concentration%20as%20they%20create%20food%20together.&width=600&height=400&seq=event4&orientation=landscape" alt="Kids Cooking Class" class="w-full h-full object-cover object-top">
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-3">
                        <h3 class="text-xl font-bold text-gray-800">Little Chefs Cooking Class</h3>
                        <span class="bg-green-100 text-green-800 text-xs font-medium px-2.5 py-1 rounded">Ages 6-10</span>
                    </div>
                    <div class="flex items-center text-gray-500 mb-3 text-sm">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-calendar-event-line"></i>
                        </div>
                        June 8, 2025 • 1:00 PM - 3:00 PM
                    </div>
                    <p class="text-gray-600 mb-4 text-sm">Kids learn to prepare simple, delicious recipes while developing kitchen safety skills and confidence.</p>
                    <button class="w-full py-2.5 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Book Now</button>
                </div>
            </div>
            <!-- Event Card 5 -->
            <div class="event-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                <div class="h-48 overflow-hidden">
                    <img src="https://readdy.ai/api/search-image?query=Children%20engaged%20in%20a%20music%20and%20movement%20class%20with%20colorful%20instruments%20like%20xylophones%2C%20drums%2C%20and%20tambourines.%20Kids%20dancing%2C%20singing%2C%20and%20playing%20instruments%20in%20a%20bright%20studio%20space.%20Professional%20photography%20showing%20joy%20and%20enthusiasm%20as%20children%20experience%20rhythm%20and%20music%20together.%20Vibrant%2C%20energetic%20atmosphere%20with%20musical%20notes%20decorations.&width=600&height=400&seq=event5&orientation=landscape" alt="Music & Movement" class="w-full h-full object-cover object-top">
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-3">
                        <h3 class="text-xl font-bold text-gray-800">Music & Movement</h3>
                        <span class="bg-purple-100 text-purple-800 text-xs font-medium px-2.5 py-1 rounded">Ages 2-5</span>
                    </div>
                    <div class="flex items-center text-gray-500 mb-3 text-sm">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-calendar-event-line"></i>
                        </div>
                        June 10, 2025 • 10:00 AM - 11:30 AM
                    </div>
                    <p class="text-gray-600 mb-4 text-sm">A lively session of songs, dance, and instrument play to develop rhythm and coordination.</p>
                    <button class="w-full py-2.5 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Book Now</button>
                </div>
            </div>
            <!-- Event Card 6 -->
            <div class="event-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                <div class="h-48 overflow-hidden">
                    <img src="https://readdy.ai/api/search-image?query=Children%20building%20with%20construction%20toys%2C%20blocks%2C%20and%20engineering%20kits.%20Kids%20working%20together%20on%20complex%20structures%20and%20simple%20machines.%20Bright%20classroom%20setting%20with%20organized%20building%20materials.%20Professional%20photography%20showing%20concentration%20and%20teamwork%20as%20children%20solve%20problems%20and%20create%20together.%20Educational%20STEM%20environment%20with%20posters%20about%20engineering%20concepts.&width=600&height=400&seq=event6&orientation=landscape" alt="Building Workshop" class="w-full h-full object-cover object-top">
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-3">
                        <h3 class="text-xl font-bold text-gray-800">Young Engineers Workshop</h3>
                        <span class="bg-green-100 text-green-800 text-xs font-medium px-2.5 py-1 rounded">Ages 7-12</span>
                    </div>
                    <div class="flex items-center text-gray-500 mb-3 text-sm">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-calendar-event-line"></i>
                        </div>
                        June 15, 2025 • 3:00 PM - 5:00 PM
                    </div>
                    <p class="text-gray-600 mb-4 text-sm">Building challenges that develop problem-solving skills and introduce basic engineering concepts.</p>
                    <button class="w-full py-2.5 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Book Now</button>
                </div>
            </div>
        </div>
        <div class="text-center mt-10">
            <button class="px-6 py-3 bg-white text-primary font-semibold rounded-button border-2 border-primary hover:bg-gray-50 transition-all flex items-center mx-auto whitespace-nowrap">
                <span>Xem Tất Cả Sự Kiện</span>
                <div class="w-5 h-5 flex items-center justify-center ml-2">
                    <i class="ri-arrow-right-line"></i>
                </div>
            </button>
        </div>
    </div>
</section>
<!-- Shop Section -->
<section id="shop" class="py-16 bg-gray-50">
    <div class="container mx-auto px-4">
        <div class="text-center mb-12">
            <h2 class="text-3xl md:text-4xl font-bold text-gray-800 mb-4">Mua Sắm Cho Bé Yêu</h2>
            <p class="text-gray-600 max-w-2xl mx-auto">Khám phá các sản phẩm được chọn lọc kỹ lưỡng kết hợp giữa niềm vui, giáo dục và chất lượng cho trẻ em ở mọi lứa tuổi.</p>
        </div>
        <!-- Category Tiles -->
        <div class="grid grid-cols-2 md:grid-cols-4 gap-4 mb-12">
            <a href="#" class="group relative rounded-lg overflow-hidden h-40 shadow-md">
                <img src="https://readdy.ai/api/search-image?query=Collection%20of%20colorful%20educational%20toys%20for%20early%20development%20on%20a%20clean%20white%20background.%20Montessori-style%20wooden%20toys%2C%20shape%20sorters%2C%20and%20sensory%20items%20neatly%20arranged.%20Professional%20product%20photography%20with%20soft%20shadows%2C%20showing%20high-quality%20childrens%20toys%20with%20educational%20value.%20Bright%2C%20cheerful%20colors%20against%20a%20minimalist%20background.&width=400&height=300&seq=cat1&orientation=landscape" alt="Educational Toys" class="w-full h-full object-cover object-top transition-transform duration-300 group-hover:scale-105">
                <div class="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent flex items-end">
                    <h3 class="text-white font-bold text-lg p-4">Đồ Chơi Giáo Dục</h3>
                </div>
            </a>
            <a href="#" class="group relative rounded-lg overflow-hidden h-40 shadow-md">
                <img src="https://readdy.ai/api/search-image?query=Collection%20of%20childrens%20books%20with%20colorful%20covers%20displayed%20on%20a%20clean%20white%20background.%20Picture%20books%2C%20early%20readers%2C%20and%20activity%20books%20neatly%20arranged.%20Professional%20product%20photography%20with%20soft%20shadows%2C%20showing%20high-quality%20childrens%20literature%20with%20educational%20value.%20Bright%2C%20engaging%20book%20covers%20against%20a%20minimalist%20background.&width=400&height=300&seq=cat2&orientation=landscape" alt="Books & Activities" class="w-full h-full object-cover object-top transition-transform duration-300 group-hover:scale-105">
                <div class="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent flex items-end">
                    <h3 class="text-white font-bold text-lg p-4">Sách & Hoạt Động</h3>
                </div>
            </a>
            <a href="#" class="group relative rounded-lg overflow-hidden h-40 shadow-md">
                <img src="https://readdy.ai/api/search-image?query=Collection%20of%20childrens%20art%20supplies%20on%20a%20clean%20white%20background.%20Colorful%20paints%2C%20brushes%2C%20crayons%2C%20modeling%20clay%2C%20and%20craft%20kits%20neatly%20arranged.%20Professional%20product%20photography%20with%20soft%20shadows%2C%20showing%20high-quality%20creative%20materials%20for%20kids.%20Vibrant%20colors%20of%20art%20supplies%20against%20a%20minimalist%20background.&width=400&height=300&seq=cat3&orientation=landscape" alt="Arts & Crafts" class="w-full h-full object-cover object-top transition-transform duration-300 group-hover:scale-105">
                <div class="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent flex items-end">
                    <h3 class="text-white font-bold text-lg p-4">Nghệ Thuật & Thủ Công</h3>
                </div>
            </a>
            <a href="#" class="group relative rounded-lg overflow-hidden h-40 shadow-md">
                <img src="https://readdy.ai/api/search-image?query=Collection%20of%20childrens%20outdoor%20play%20equipment%20on%20a%20clean%20white%20background.%20Balls%2C%20jump%20ropes%2C%20frisbees%2C%20and%20small%20sports%20gear%20neatly%20arranged.%20Professional%20product%20photography%20with%20soft%20shadows%2C%20showing%20high-quality%20outdoor%20toys%20for%20active%20play.%20Bright%2C%20colorful%20play%20equipment%20against%20a%20minimalist%20background.&width=400&height=300&seq=cat4&orientation=landscape" alt="Outdoor Play" class="w-full h-full object-cover object-top transition-transform duration-300 group-hover:scale-105">
                <div class="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent flex items-end">
                    <h3 class="text-white font-bold text-lg p-4">Vui Chơi Ngoài Trời</h3>
                </div>
            </a>
        </div>
        <!-- Featured Products -->
        <div class="mb-8">
            <div class="flex justify-between items-center mb-6">
                <h3 class="text-2xl font-bold text-gray-800">Sản Phẩm Nổi Bật</h3>
                <div class="flex gap-2">
                    <button id="prev-product" class="w-10 h-10 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100 whitespace-nowrap">
                        <i class="ri-arrow-left-s-line"></i>
                    </button>
                    <button id="next-product" class="w-10 h-10 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100 whitespace-nowrap">
                        <i class="ri-arrow-right-s-line"></i>
                    </button>
                </div>
            </div>
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                <!-- Product Card 1 -->
                <div class="product-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                    <div class="relative h-48 overflow-hidden">
                        <img src="https://readdy.ai/api/search-image?query=Wooden%20alphabet%20blocks%20educational%20toy%20set%20for%20children%20on%20a%20clean%20white%20background.%20High-quality%20wooden%20blocks%20with%20colorful%20letters%20and%20numbers.%20Professional%20product%20photography%20with%20soft%20shadows%20showing%20details%20and%20texture%20of%20the%20wooden%20blocks.%20Bright%2C%20educational%20toy%20against%20minimalist%20background.&width=400&height=400&seq=prod1&orientation=squarish" alt="Alphabet Blocks" class="w-full h-full object-cover object-top">
                        <div class="absolute top-3 right-3">
                            <span class="bg-primary text-white text-xs font-medium px-2 py-1 rounded">Bestseller</span>
                        </div>
                    </div>
                    <div class="p-4">
                        <div class="flex justify-between items-start mb-2">
                            <h3 class="font-medium text-gray-800">Bộ Khối Chữ Cái Bằng Gỗ</h3>
                            <span class="text-sm text-gray-500">Từ 2 Tuổi</span>
                        </div>
                        <div class="flex justify-between items-center mb-3">
                            <span class="text-primary font-bold">599.000₫</span>
                            <div class="flex items-center">
                                <div class="flex text-yellow-400">
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-half-fill"></i>
                                </div>
                                <span class="text-xs text-gray-500 ml-1">(42)</span>
                            </div>
                        </div>
                        <button class="w-full py-2 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Thêm Vào Giỏ</button>
                    </div>
                </div>
                <!-- Product Card 2 -->
                <div class="product-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                    <div class="relative h-48 overflow-hidden">
                        <img src="https://readdy.ai/api/search-image?query=Childrens%20art%20easel%20with%20paint%20supplies%20on%20a%20clean%20white%20background.%20Wooden%20easel%20with%20paper%2C%20paint%20pots%2C%20and%20brushes%20included.%20Professional%20product%20photography%20with%20soft%20shadows%20showing%20details%20of%20the%20art%20station.%20Colorful%2C%20creative%20art%20setup%20against%20minimalist%20background.&width=400&height=400&seq=prod2&orientation=squarish" alt="Art Easel" class="w-full h-full object-cover object-top">
                        <div class="absolute top-3 right-3">
                            <span class="bg-green-500 text-white text-xs font-medium px-2 py-1 rounded">New</span>
                        </div>
                    </div>
                    <div class="p-4">
                        <div class="flex justify-between items-start mb-2">
                            <h3 class="font-medium text-gray-800">Children's Art Easel Set</h3>
                            <span class="text-sm text-gray-500">Ages 3+</span>
                        </div>
                        <div class="flex justify-between items-center mb-3">
                            <span class="text-primary font-bold">$39.99</span>
                            <div class="flex items-center">
                                <div class="flex text-yellow-400">
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-line"></i>
                                </div>
                                <span class="text-xs text-gray-500 ml-1">(28)</span>
                            </div>
                        </div>
                        <button class="w-full py-2 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Add to Cart</button>
                    </div>
                </div>
                <!-- Product Card 3 -->
                <div class="product-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                    <div class="relative h-48 overflow-hidden">
                        <img src="https://readdy.ai/api/search-image?query=Childrens%20science%20experiment%20kit%20with%20test%20tubes%2C%20microscope%2C%20and%20educational%20materials%20on%20a%20clean%20white%20background.%20Complete%20science%20kit%20with%20colorful%20components%20neatly%20arranged%20in%20a%20carrying%20case.%20Professional%20product%20photography%20with%20soft%20shadows%20showing%20details%20of%20the%20educational%20science%20set.%20STEM%20learning%20toy%20against%20minimalist%20background.&width=400&height=400&seq=prod3&orientation=squarish" alt="Science Kit" class="w-full h-full object-cover object-top">
                    </div>
                    <div class="p-4">
                        <div class="flex justify-between items-start mb-2">
                            <h3 class="font-medium text-gray-800">Junior Scientist Kit</h3>
                            <span class="text-sm text-gray-500">Ages 8+</span>
                        </div>
                        <div class="flex justify-between items-center mb-3">
                            <span class="text-primary font-bold">$32.50</span>
                            <div class="flex items-center">
                                <div class="flex text-yellow-400">
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                </div>
                                <span class="text-xs text-gray-500 ml-1">(56)</span>
                            </div>
                        </div>
                        <button class="w-full py-2 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Add to Cart</button>
                    </div>
                </div>
                <!-- Product Card 4 -->
                <div class="product-card bg-white rounded-lg shadow-md overflow-hidden transition-all duration-300">
                    <div class="relative h-48 overflow-hidden">
                        <img src="https://readdy.ai/api/search-image?query=Colorful%20childrens%20balance%20bike%20on%20a%20clean%20white%20background.%20Lightweight%2C%20adjustable%20bike%20without%20pedals%20for%20toddlers%20learning%20to%20balance.%20Professional%20product%20photography%20with%20soft%20shadows%20showing%20details%20of%20the%20sturdy%2C%20safe%20design.%20Bright%2C%20outdoor%20toy%20against%20minimalist%20background.&width=400&height=400&seq=prod4&orientation=squarish" alt="Balance Bike" class="w-full h-full object-cover object-top">
                        <div class="absolute top-3 right-3">
                            <span class="bg-blue-500 text-white text-xs font-medium px-2 py-1 rounded">Sale</span>
                        </div>
                    </div>
                    <div class="p-4">
                        <div class="flex justify-between items-start mb-2">
                            <h3 class="font-medium text-gray-800">Toddler Balance Bike</h3>
                            <span class="text-sm text-gray-500">Ages 2-5</span>
                        </div>
                        <div class="flex justify-between items-center mb-3">
                            <div>
                                <span class="text-primary font-bold">$49.99</span>
                                <span class="text-gray-400 text-sm line-through ml-2">$65.99</span>
                            </div>
                            <div class="flex items-center">
                                <div class="flex text-yellow-400">
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-fill"></i>
                                    <i class="ri-star-half-fill"></i>
                                </div>
                                <span class="text-xs text-gray-500 ml-1">(37)</span>
                            </div>
                        </div>
                        <button class="w-full py-2 bg-primary text-white font-medium rounded-button hover:bg-opacity-90 transition-colors whitespace-nowrap">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="text-center mt-8">
            <button class="px-6 py-3 bg-white text-primary font-semibold rounded-button border-2 border-primary hover:bg-gray-50 transition-all flex items-center mx-auto whitespace-nowrap">
                <span>Browse All Products</span>
                <div class="w-5 h-5 flex items-center justify-center ml-2">
                    <i class="ri-arrow-right-line"></i>
                </div>
            </button>
        </div>
    </div>
</section>
<!-- Benefits Banner -->
<section class="py-16 bg-white">
    <div class="container mx-auto px-4">
        <div class="text-center mb-12">
            <h2 class="text-3xl md:text-4xl font-bold text-gray-800 mb-4">Tại Sao Phụ Huynh Chọn Chúng Tôi</h2>
            <p class="text-gray-600 max-w-2xl mx-auto">Chúng tôi luôn đặt sự an toàn, phát triển và niềm vui của con bạn lên hàng đầu trong mọi hoạt động.</p>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div class="text-center p-6">
                <div class="w-16 h-16 bg-primary bg-opacity-10 rounded-full flex items-center justify-center mx-auto mb-4">
                    <i class="ri-shield-check-line text-2xl text-primary"></i>
                </div>
                <h3 class="text-xl font-bold text-gray-800 mb-3">An Toàn Là Trên Hết</h3>
                <p class="text-gray-600">Tất cả sự kiện và sản phẩm của chúng tôi đều đáp ứng các tiêu chuẩn an toàn cao nhất. Nhân viên được đào tạo đầy đủ và kiểm tra lý lịch kỹ lưỡng.</p>
            </div>
            <div class="text-center p-6">
                <div class="w-16 h-16 bg-secondary bg-opacity-10 rounded-full flex items-center justify-center mx-auto mb-4">
                    <i class="ri-book-open-line text-2xl text-secondary"></i>
                </div>
                <h3 class="text-xl font-bold text-gray-800 mb-3">Giá Trị Giáo Dục</h3>
                <p class="text-gray-600">Các hoạt động và sản phẩm của chúng tôi được thiết kế bởi các chuyên gia phát triển trẻ em để hỗ trợ các mốc học tập quan trọng.</p>
            </div>
            <div class="text-center p-6">
                <div class="w-16 h-16 bg-yellow-400 bg-opacity-10 rounded-full flex items-center justify-center mx-auto mb-4">
                    <i class="ri-emotion-laugh-line text-2xl text-yellow-500"></i>
                </div>
                <h3 class="text-xl font-bold text-gray-800 mb-3">Đảm Bảo Vui Vẻ</h3>
                <p class="text-gray-600">Chúng tôi tin rằng việc học tập tốt nhất thông qua vui chơi. Phương pháp của chúng tôi đảm bảo trẻ em được tham gia và tận hưởng niềm vui.</p>
            </div>
        </div>
        <div class="mt-12 flex flex-wrap justify-center gap-6">
            <div class="flex items-center bg-gray-100 px-4 py-2 rounded-full">
                <div class="w-8 h-8 flex items-center justify-center mr-2">
                    <i class="ri-award-line text-gray-700"></i>
                </div>
                <span class="text-sm font-medium text-gray-700">Chứng Nhận An Toàn</span>
            </div>
            <div class="flex items-center bg-gray-100 px-4 py-2 rounded-full">
                <div class="w-8 h-8 flex items-center justify-center mr-2">
                    <i class="ri-parent-line text-gray-700"></i>
                </div>
                <span class="text-sm font-medium text-gray-700">Phụ Huynh Tin Tưởng</span>
            </div>
            <div class="flex items-center bg-gray-100 px-4 py-2 rounded-full">
                <div class="w-8 h-8 flex items-center justify-center mr-2">
                    <i class="ri-heart-line text-gray-700"></i>
                </div>
                <span class="text-sm font-medium text-gray-700">Trẻ Em Yêu Thích</span>
            </div>
            <div class="flex items-center bg-gray-100 px-4 py-2 rounded-full">
                <div class="w-8 h-8 flex items-center justify-center mr-2">
                    <i class="ri-recycle-line text-gray-700"></i>
                </div>
                <span class="text-sm font-medium text-gray-700">Thân Thiện Môi Trường</span>
            </div>
        </div>
    </div>
</section>
<!-- Newsletter Signup -->
<section class="py-16 bg-gradient-to-r from-primary/10 to-secondary/10 relative overflow-hidden">
    <div class="absolute top-0 right-0 w-64 h-64 bg-primary rounded-full opacity-5 -mr-32 -mt-32"></div>
    <div class="absolute bottom-0 left-0 w-64 h-64 bg-secondary rounded-full opacity-5 -ml-32 -mb-32"></div>
    <div class="container mx-auto px-4 relative z-10">
        <div class="max-w-2xl mx-auto text-center">
            <h2 class="text-3xl font-bold text-gray-800 mb-4">Tham Gia Gia Đình KidZone</h2>
            <p class="text-gray-600 mb-8">Đăng ký để nhận thông tin cập nhật về các sự kiện sắp tới, sản phẩm mới và ưu đãi độc quyền cho bé yêu của bạn.</p>
            <div class="flex flex-col sm:flex-row gap-4 mb-6">
                <input type="email" placeholder="Địa chỉ email của bạn" class="flex-grow px-4 py-3 rounded-lg border-none shadow-sm focus:outline-none focus:ring-2 focus:ring-primary text-sm">
                <button class="px-6 py-3 bg-primary text-white font-semibold rounded-button shadow-md hover:bg-opacity-90 transition-all whitespace-nowrap">Đăng Ký Ngay</button>
            </div>
            <label class="custom-checkbox inline-block text-left text-sm text-gray-600">
                <input type="checkbox">
                <span class="checkmark"></span>
                Tôi đồng ý nhận email về sự kiện và ưu đãi. Bạn có thể hủy đăng ký bất cứ lúc nào.
            </label>
        </div>
    </div>
</section>
<!-- Footer -->
<footer class="bg-gray-800 text-white pt-16 pb-8">
    <div class="container mx-auto px-4">
        <div class="grid grid-cols-1 md:grid-cols-4 gap-8 mb-12">
            <div>
                <a href="#" class="text-3xl font-['Pacifico'] text-white mb-4 inline-block">logo</a>
                <p class="text-gray-400 mb-6">Tạo nên những khoảnh khắc diệu kỳ và hỗ trợ sự phát triển của trẻ thông qua vui chơi và giáo dục.</p>
                <div class="flex space-x-4">
                    <a href="#" class="w-10 h-10 rounded-full bg-gray-700 flex items-center justify-center hover:bg-primary transition-colors">
                        <i class="ri-facebook-fill"></i>
                    </a>
                    <a href="#" class="w-10 h-10 rounded-full bg-gray-700 flex items-center justify-center hover:bg-primary transition-colors">
                        <i class="ri-instagram-line"></i>
                    </a>
                    <a href="#" class="w-10 h-10 rounded-full bg-gray-700 flex items-center justify-center hover:bg-primary transition-colors">
                        <i class="ri-twitter-x-line"></i>
                    </a>
                    <a href="#" class="w-10 h-10 rounded-full bg-gray-700 flex items-center justify-center hover:bg-primary transition-colors">
                        <i class="ri-youtube-line"></i>
                    </a>
                </div>
            </div>
            <div>
                <h4 class="text-lg font-bold mb-4">Liên Kết Nhanh</h4>
                <ul class="space-y-2">
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Lịch Sự Kiện</a></li>
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Tất Cả Sản Phẩm</a></li>
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Về Đội Ngũ</a></li>
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Thuê Địa Điểm</a></li>
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Thẻ Quà Tặng</a></li>
                </ul>
            </div>
            <div>
                <h4 class="text-lg font-bold mb-4">Hỗ Trợ</h4>
                <ul class="space-y-2">
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Liên Hệ</a></li>
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Câu Hỏi Thường Gặp</a></li>
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Vận Chuyển & Đổi Trả</a></li>
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Chính Sách Bảo Mật</a></li>
                    <li><a href="#" class="text-gray-400 hover:text-white transition-colors">Điều Khoản Dịch Vụ</a></li>
                </ul>
            </div>
            <div>
                <h4 class="text-lg font-bold mb-4">Liên Hệ Với Chúng Tôi</h4>
                <ul class="space-y-3">
                    <li class="flex items-start">
                        <div class="w-5 h-5 flex items-center justify-center mr-2 mt-0.5">
                            <i class="ri-map-pin-line"></i>
                        </div>
                        <span class="text-gray-400">123 Playful Avenue, Kidsville, NY 10001</span>
                    </li>
                    <li class="flex items-center">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-phone-line"></i>
                        </div>
                        <span class="text-gray-400">(555) 123-4567</span>
                    </li>
                    <li class="flex items-center">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-mail-line"></i>
                        </div>
                        <span class="text-gray-400">hello@kidzone.com</span>
                    </li>
                    <li class="flex items-center">
                        <div class="w-5 h-5 flex items-center justify-center mr-2">
                            <i class="ri-time-line"></i>
                        </div>
                        <span class="text-gray-400">Mon-Sat: 9AM-6PM</span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="pt-8 border-t border-gray-700 flex flex-col md:flex-row justify-between items-center">
            <p class="text-gray-400 text-sm mb-4 md:mb-0">© 2025 KidZone. Đã đăng ký bản quyền.</p>
            <div class="flex flex-wrap justify-center gap-4">
                <div class="w-10 h-6 flex items-center justify-center">
                    <i class="ri-visa-fill text-2xl text-gray-300"></i>
                </div>
                <div class="w-10 h-6 flex items-center justify-center">
                    <i class="ri-mastercard-fill text-2xl text-gray-300"></i>
                </div>
                <div class="w-10 h-6 flex items-center justify-center">
                    <i class="ri-paypal-fill text-2xl text-gray-300"></i>
                </div>
                <div class="w-10 h-6 flex items-center justify-center">
                    <i class="ri-apple-fill text-2xl text-gray-300"></i>
                </div>
                <div class="w-10 h-6 flex items-center justify-center">
                    <i class="ri-google-fill text-2xl text-gray-300"></i>
                </div>
            </div>
        </div>
    </div>
</footer>
<script id="carousel-control">
    document.addEventListener('DOMContentLoaded', function() {
        const prevButton = document.getElementById('prev-product');
        const nextButton = document.getElementById('next-product');
        const productCards = document.querySelectorAll('.product-card');
        let currentIndex = 0;
// This is a simplified carousel for demonstration
// In a real implementation, you would slide the products
        prevButton.addEventListener('click', function() {
            currentIndex = Math.max(currentIndex - 1, 0);
            updateCarousel();
        });
        nextButton.addEventListener('click', function() {
            currentIndex = Math.min(currentIndex + 1, productCards.length - 4);
            updateCarousel();
        });
        function updateCarousel() {
// This would be implemented with actual sliding animation
// For this demo, we'll just log the action
            console.log('Carousel moved to index:', currentIndex);
        }
    });
</script>
<script id="mobile-menu">
    document.addEventListener('DOMContentLoaded', function() {
        const menuButton = document.querySelector('.ri-menu-line').parentElement;
// This would be implemented with a full mobile menu
// For this demo, we'll just log the action
        menuButton.addEventListener('click', function() {
            console.log('Mobile menu toggled');
        });
    });
</script>
<script id="checkbox-handler">
    document.addEventListener('DOMContentLoaded', function() {
        const checkboxes = document.querySelectorAll('.custom-checkbox input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function() {
                console.log('Checkbox state changed:', this.checked);
            });
        });
    });
</script>

<script>
    var stompClient = null;

    function setConnected(connected) {
        var conversationDiv = document.getElementById('conversationDiv');
        var response = document.getElementById('response');

        // Kiểm tra nếu các phần tử tồn tại
        if (conversationDiv && response) {
            conversationDiv.style.visibility = connected ? 'visible' : 'hidden';
            response.innerHTML = '';
        }
    }

    window.onload = function() {
        var account = '${sessionScope.account != null ? sessionScope.account.username : ""}'; // Lấy thông tin người dùng từ session

        if (account !== "") {
            // Người dùng đã đăng nhập, tự động kết nối WebSocket
            connect();

    };


    function connect() {
        var socket = new SockJS('/Baby_war/chat');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            // Subscribe nhận tin nhắn
            var userId = document.getElementById('fromId').value; // ID người dùng hiện tại
            stompClient.subscribe('/topic/messages', function(message) {
                showMessage(JSON.parse(message.body));
            });

            // Subscribe nhận thông báo
            stompClient.subscribe('/user/' + userId + '/queue/notifications', function(notification) {
                showNotification(JSON.parse(notification.body));
            });
        });
    }

    function showNotification(notification) {
        alert(notification.message); // Hoặc hiển thị thông báo trong UI
    }


    function sendMessage() {
        var toId = document.getElementById('toId').value;
        var fromId = document.getElementById('fromId').value;
        var messageText = document.getElementById('messageText').value;

        if (toId && fromId && messageText) {
            var message = {
                toId: toId,
                fromId: fromId,
                messageText: messageText,
                timestamp: new Date().toISOString()
            };

            stompClient.send("/app/chat", {}, JSON.stringify(message));
        } else {
            alert("Please fill in all fields!");
        }
    }

    function showMessage(message) {
        var toId = document.getElementById('toId').value;
        var fromId = document.getElementById('fromId').value;

        if ((message.toId === toId && message.fromId === fromId) || (message.toId === fromId && message.fromId === toId)) {
            var response = document.getElementById('response');
            var messageElement = document.createElement('div');
            messageElement.className = 'chat-message';

            if (message.fromId === fromId) {
                messageElement.classList.add('from-current-user');
            } else {
                messageElement.classList.add('from-other-user');
            }

            var p = document.createElement('p');
            p.appendChild(document.createTextNode(message.messageText));
            messageElement.appendChild(p);

            response.appendChild(messageElement);

            response.scrollTop = response.scrollHeight;
        }
    }
    }

</script>
</body>
</html>
