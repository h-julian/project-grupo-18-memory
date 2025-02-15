document.addEventListener('DOMContentLoaded', (event) => {
    const darkModeSwitch = document.getElementById('darkModeSwitch');
    const isDarkMode = localStorage.getItem('darkMode') === 'true';
    if (isDarkMode) {
        document.documentElement.classList.add('dark-mode');
        if (darkModeSwitch) {
            darkModeSwitch.checked = true;
        }
    }

    if (darkModeSwitch) {
        darkModeSwitch.addEventListener('change', () => {
            document.documentElement.classList.toggle('dark-mode');
            localStorage.setItem('darkMode', document.documentElement.classList.contains('dark-mode'));
        });
    }
});