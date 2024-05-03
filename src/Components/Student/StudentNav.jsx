import React from "react";
import { Link } from "react-router-dom";

const StudentNav = () => {
  return (
    <div>
      <nav class="mx-auto block w-full max-w-screen-xl rounded-xl border border-white/80 bg-blue bg-opacity-80 py-2 px-4 text-white shadow-md backdrop-blur-2xl backdrop-saturate-200 lg:px-8 lg:py-4">
        <div>
          <div class="container mx-auto flex items-center justify-between text-gray-900">
            <a
              href="#"
              class="mr-4 block cursor-pointer py-1.5 font-sans text-xl font-bold text-violet-500  leading-normal text-inherit antialiased"
            >
              <span>SCHOLARSHIP SEARCH SYSTEM</span>
            </a>
            <ul class="hidden items-center gap-6 lg:flex">
            <li class="block p-1 font-sans text-sm font-normal leading-normal text-inherit antialiased">
                <Link class="flex items-center font-bold text-lg" to={"/"}>
                  Home
                </Link>
              </li>
              <li class="block p-1 font-sans text-sm font-normal leading-normal text-inherit antialiased">
                <Link class="flex items-center font-bold text-lg" to={"/"}>
                  Logout
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  );
};

export default StudentNav;
