package Model;

interface modelGeneric<U> {
    public boolean save(U u);
    public boolean edit(U u);
    public boolean delete(U u);
    public void view(U u);
}
