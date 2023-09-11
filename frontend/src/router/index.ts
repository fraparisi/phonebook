import { createRouter, createWebHistory } from 'vue-router';
import AddContactView from "../views/AddContactView.vue";
import LoginView from "../views/LoginView.vue";
import ListContactView from "../views/ListContactView.vue";
import EditContactView from "../views/EditContactView.vue";
import ShowContactView from "../views/ShowContactView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: "/contacts",
      name: "contacts",
      component: ListContactView
    },
    {
      path: "/edit/contact/:id",
      name: "edit",
      component: EditContactView,
      props: true,
    },
    {
      path: "/show/contact/:id",
      name: "details",
      component: ShowContactView,
      props: true,
    },
    {
      path: "/add/contact",
      name: "add",
      component: AddContactView,
      props: true,
    },
    // {
    //   path: "/details/contact/:id",
    //   name: "details",
    //   component: ShowDetails,
    // },
  ]
})

export default router
