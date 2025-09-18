# LocatorBarRange

A Minecraft plugin for Paper-based servers that customizes how far players can see and send locator bar waypoints per-world and per-permission group.

## Features

- **Per-World Ranges**: Configure default view and send ranges for each world.
- **Permission Overrides**: Create named override groups with priorities and permissions to further customize ranges.
- **Dynamic Updates**: Automatically applies range settings when players join or when reloading the configuration.
- **Tab-Complete `/locatorbarrange` Command**: Supports `/locatorbarrange reload` with tab completion.

## Installation

1. Download the latest `LocatorBarRange.jar` and place it in your server's `plugins/` directory.
2. Start or restart your server to generate the default `config.yml`.
3. (Optional) Customize `plugins/LocatorBarRange/config.yml`.
4. Reload the plugin or restart your server to apply changes.

## Configuration (config.yml)
https://github.com/TechnicallyCoded/LocatorBarRange/blob/main/src/main/resources/config.yml

## Commands

### /locatorbarrange reload

Reloads the plugin configuration from disk.

- **Alias**: `/lbr reload`
- **Permission**: `locatorbarrange.group.admin`

Example:
```
/lbr reload
``` 

## Permissions

- `locatorbarrange.group.admin` — Grants access to the admin override group and `/locatorbarrange reload` command.

## Contributing

Pull requests are welcome! Please follow the code style and test your changes.

## License

LGPLv3 License. See [LICENSE](LICENSE) for details.

