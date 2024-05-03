import React, { useEffect, useState } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import { Link, useNavigate } from "react-router-dom";
import AdminNav from "./AdminNav";

const ViewUniversity = () => {
  const navigate = useNavigate();
  const [data, setData] = useState([]);

  const getUniversities = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/v1/university`);
      setData(res.data);
    } catch (error) {
      console.error("Error fetching universities:", error);
      toast.error("Failed to fetch universities. Please try again.");
    }
  };

  useEffect(() => {
    getUniversities();
  }, []);

  const handleDeleteUniversity = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/university/${id}`);
      toast.success("University deleted successfully!");
      setData(data.filter((item) => item.id !== id));
    } catch (error) {
      console.error("Error deleting university:", error);
      toast.error("Failed to delete university. Please try again.");
    }
  };

  return (
    <div>
      <AdminNav />
      <div className="flex justify-center mt-3 bg-clip-padding">
        <h2 className="text-4xl text-black font-bold p-3 border-4 rounded-lg">
          UNIVERSITY LIST
        </h2>
      </div>
      <div className="flex justify-end mt-3 bg-clip-padding">
        <h2 className="text-xl bg-neutral-300 text-black font-bold p-1 mr-20 border-4 rounded-lg">
          <Link to={"/addUniversity"}>ADD UNIVERSITY</Link>
        </h2>
      </div>
      <div className="flex justify-center mt-5">
        <table className="text-center text-sm bg-white w-8/12 border-8 border-blue-200">
          <thead className="border-b font-medium border-4 border-pink-500">
            <tr>
              <th scope="col" className="px-6 py-2 text-lg">
                Email
              </th>
              <th scope="col" className="px-6 py-2 text-lg">
                Location
              </th>
              <th scope="col" className="px-6 py-2 text-lg">
                Action
              </th>
            </tr>
          </thead>
          <tbody>
            {data.map((item) => (
              <tr key={item.id} className="border-b dark:border-neutral-500">
                <td className="whitespace-nowrap px-6 py-2">{item.name}</td>
                <td className="whitespace-nowrap px-6 py-2">{item.location}</td>
                <td className="whitespace-nowrap px-6 py-2">
                  <button
                    className="mt-2 block w-full select-none rounded-lg bg-red-500 py-2 px-4 align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                    type="button"
                    onClick={() => handleDeleteUniversity(item.id)}
                    data-ripple-light="true"
                  >
                    DELETE UNIVERSITY
                  </button>
                  <button
                    className="mt-2 block w-full select-none rounded-lg bg-yellow-400 py-2 px-4 align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                    type="submit"
                    onClick={() => navigate(`/addscholarship/${item.id}`)}
                    data-ripple-light="true"
                  >
                    ADD SCHOLARSHIP
                  </button>
                  <button
                    className="mt-2 block w-full select-none rounded-lg bg-green-400 py-2 px-4 align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                    type="submit"
                    onClick={() => navigate(`/adminViewScholarships/${item.id}`)}
                    data-ripple-light="true"
                  >
                    VIEW SCHOLARSHIP
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewUniversity;
