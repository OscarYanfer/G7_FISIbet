import Link from "next/link";
import styles from "./index.module.scss";

const Navbar = () => {
  return (
    <nav className={styles.navbar}>
      {/* <Link href="/login">
        <p className={styles.logo}>FISIbet</p>
      </Link>
      <Link href="/login">
        <p className={styles.navLinks}>Apuestas deportivas</p>
      </Link>
      <Link href="/login">
        <p className={styles.navLinks}>Promociones</p>
      </Link>
      <Link href="/login">
        <p className={styles.navLinks}>Terminos y condiciones</p>
      </Link> */}
      <ul className={styles.navLinks}>
        <li>
          <Link href="/login">
            <p className={styles.logo}>FISIbet</p>
          </Link>
        </li>
        <li>
          <Link href="/login">
            <p className={styles.navLinks}>Apuestas deportivas</p>
          </Link>
        </li>
        <li>
          <Link href="/login">
            <p className={styles.navLinks}>Promociones</p>
          </Link>
        </li>
        <li>
          <Link href="/login">
            <p className={styles.navLinks}>Terminos y condiciones</p>
          </Link>
        </li>
      </ul>

      <ul className={styles.navLinks}>
        <li>
          <Link href="/login">
            {/* <p className={styles.navLink}>Apuestas</p> */}
            <button className={styles.buttonPrimary}>Iniciar sesión</button>
          </Link>
        </li>
        <li>
          <Link href="/register">
            {/* <p className={styles.navLink}>Deportes</p> */}
            <button className={styles.buttonPrimary}>Registrarse</button>
          </Link>
        </li>
        {/* <li>
          <Link href="/casino">
            <p className={styles.navLink}>Casino</p>
          </Link>
        </li> */}
      </ul>
    </nav>
  );
};

export default Navbar;
