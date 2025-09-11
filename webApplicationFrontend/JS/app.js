const API_BASE = "http://localhost:8092/api/v1/notes";

const page = document.getElementById('page');
const modal = document.getElementById('modal');
const addNoteBtn = document.getElementById('addNoteBtn');
const closeModal = document.querySelector('.close');
const saveNoteBtn = document.getElementById('saveNoteBtn');
const modalTitle = document.getElementById('modalTitle');

let currentNoteId = null; // This will hold note ID when editing

// ------------------ ROUTER ------------------
function router() {
    const hash = window.location.hash || "#/";
    if (hash === "#/") {
        renderNotesList();
    } else if (hash.startsWith("#/note/")) {
        const id = hash.split("/")[2];
        renderNoteDetail(id);
    }
}

// ------------------ FETCH NOTES ------------------
async function fetchNotes() {
    try {
        const res = await fetch(`${API_BASE}/all-notes`);
        if (!res.ok) throw new Error("Failed to fetch notes");
        return await res.json();
    } catch (err) {
        showInlineMessage(err.message);
        return [];
    }
}

// ------------------ RENDER NOTES LIST ------------------
async function renderNotesList() {
    const notes = await fetchNotes();
    page.innerHTML = '';
    if (notes.length === 0) {
        page.innerHTML = '<p>No notes found. Click "Add Note" to create one.</p>';
        return;
    }

    notes.forEach(note => {
        const card = document.createElement('div');
        card.className = 'note-card';
        card.innerHTML = `
            <h4>${note.title}</h4>
            <p>${new Date(note.createdTime || note.createdAt).toLocaleString()}</p>
        `;
        const noteId = note.id || note.noteId;
        card.onclick = () => window.location.hash = `#/note/${noteId}`; // Go to detail page
        page.appendChild(card);
    });
}

// ------------------ OPEN MODAL ------------------
addNoteBtn.onclick = () => openModal(); // For Add Note

function openModal(note = null) {
    modal.style.display = 'block';
    clearInlineMessage();

    if (note) {
        modalTitle.textContent = 'Edit Note';
        document.getElementById('noteTitle').value = note.title || '';
        document.getElementById('noteContent').value = note.content || '';
        currentNoteId = note.id || note.noteId; // Set ID for update
    } else {
        modalTitle.textContent = 'Add New Note';
        document.getElementById('noteTitle').value = '';
        document.getElementById('noteContent').value = '';
        currentNoteId = null; // Clear ID for add
    }
}

// ------------------ CLOSE MODAL ------------------
closeModal.onclick = () => modal.style.display = 'none';
window.onclick = (e) => { if(e.target == modal) modal.style.display = 'none'; };

// ------------------ SAVE NOTE WITH VALIDATION ------------------
saveNoteBtn.onclick = async () => {
    const title = document.getElementById('noteTitle').value.trim();
    const content = document.getElementById('noteContent').value.trim();

    let errors = [];
    if (!title) errors.push("Title is required.");
    if (!content) errors.push("Content is required.");
    if (title.length > 50) errors.push("Title cannot exceed 50 characters.");
    if (content.length > 200) errors.push("Content cannot exceed 200 characters.");

    if (errors.length > 0) {
        showInlineMessage(errors.join(" "));
        return;
    }

    try {
        if (currentNoteId) {
            // UPDATE NOTE
            const res = await fetch(`${API_BASE}/update-notes`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ noteId: currentNoteId, title, content })
            });
            if (!res.ok) throw new Error(await res.text());
        } else {
            // ADD NOTE
            const res = await fetch(`${API_BASE}/add`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ title, content })
            });
            if (!res.ok) throw new Error(await res.text());
        }

        modal.style.display = 'none';
        currentNoteId = null;
        router(); // Refresh page
    } catch (err) {
        showInlineMessage("Failed to save note: " + err.message);
    }
};

// ------------------ RENDER NOTE DETAIL ------------------
async function renderNoteDetail(id) {
    try {
        const res = await fetch(`${API_BASE}/${id}`);
        if (!res.ok) throw new Error("Note not found");
        const note = await res.json();

        page.innerHTML = `
            <h2>${note.title || ''}</h2>
            <p>Created: ${new Date(note.createdTime || note.createdAt).toLocaleString()}</p>
            <p>${note.content || ''}</p>
            <button id="editNoteBtn">Edit</button>
            <button id="deleteNoteBtn">Delete</button>
            <button onclick="window.location.hash='#/'">Back</button>
        `;

        document.getElementById('editNoteBtn').onclick = () => openModal(note); // Proper edit
        document.getElementById('deleteNoteBtn').onclick = async () => {
            if (!confirm("Are you sure you want to delete this note?")) return;
            const deleteRes = await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });
            if (!deleteRes.ok) throw new Error(await deleteRes.text());
            window.location.hash = "#/"; // Go back to list
        };

    } catch (err) {
        showInlineMessage(err.message);
        window.location.hash = "#/";
    }
}

// ------------------ INLINE MESSAGE ------------------
function showInlineMessage(msg) {
    let msgDiv = document.getElementById('formMessage');
    if (!msgDiv) {
        msgDiv = document.createElement('div');
        msgDiv.id = 'formMessage';
        msgDiv.style.color = 'red';
        msgDiv.style.marginBottom = '10px';
        msgDiv.style.fontSize = '14px';
        const modalContent = document.querySelector('.modal-content');
        if(modalContent) modalContent.prepend(msgDiv);
    }
    msgDiv.textContent = msg;
}

function clearInlineMessage() {
    const msgDiv = document.getElementById('formMessage');
    if (msgDiv) msgDiv.textContent = '';
}

// ------------------ INITIALIZE ------------------
window.addEventListener('hashchange', router);
window.addEventListener('load', router);
