
interface Manageable {
  void display();
}

abstract class User implements Manageable {
  private String id;
  private String username;
  private String password;

  public User(String id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public String getID() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public abstract String getType();

  public void display() {
    System.out.println("ID: " + id);
    System.out.println("Username: " + username);
    System.out.println("Type: " + getType());
  }

  public String toFile() {
    return id + "," + username + "," + getType();
  }


}